package com.fern.automate.issue

import arrow.core.left
import arrow.core.right
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import com.fern.automate.base.Constants
import com.fern.automate.base.github.GitHubIssueArgs
import com.fern.automate.base.github.GitHubService
import com.fern.automate.base.github.model.GitHubComment
import com.fern.automate.base.github.model.GitHubIssue
import com.fern.automate.base.github.model.GitHubIssueNumber
import com.fern.automate.base.github.model.GitHubLabel
import com.fern.automate.base.github.model.GitHubLabelName
import com.fern.automate.base.github.model.GitHubPAT
import com.fern.automate.base.github.model.GitHubUser
import com.fern.automate.base.github.model.GitHubUsername
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DetermineActionTest {
    val user1 = GitHubUser(GitHubUsername("user1"))
    val user2 = GitHubUser(GitHubUsername("user2"))
    val ivyBot = GitHubUser(GitHubUsername(Constants.IVY_BOT_USERNAME))

    val issueNumber = GitHubIssueNumber("1234")
    val args = GitHubIssueArgs(
        pat = GitHubPAT("pat"),
        issueNumber = issueNumber
    )
    val gitHubService = mockk<GitHubService>()

    fun testScope(
        block: suspend context(GitHubService) () -> Unit
    ) {
        runTest {
            block(gitHubService)
        }
    }

    fun newLabel(name: String) = GitHubLabel(GitHubLabelName(name))
    fun newIssue(
        number: GitHubIssueNumber = issueNumber,
        creator: GitHubUser = user1,
        assignee: GitHubUser? = null,
    ) = GitHubIssue(
        number = number,
        creator = creator,
        assignee = assignee,
    )

    @Test
    fun `unhappy path - fails to fetch comments`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns Exception("API error").left()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeLeft()
    }

    @Test
    fun `unhappy path - fails to fetch issue`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user1, "I'm on it")
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns Exception("API error").left()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeLeft()
    }

    @Test
    fun `unhappy path - fails to fetch labels`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user1, "I'm on it")
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns Exception("API error").left()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeLeft()
    }

    @Test
    fun `happy path - AssignIssue - single comment`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user1, "I'm on it")
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeRight() shouldBe Action.AssignIssue(issueNumber, user1)
    }

    @Test
    fun `happy path -AssignIssue - multiple comments`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user1, "Can I take it?"),
            GitHubComment(ivyBot, "Random message"),
            GitHubComment(user2, "I'm on it")
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeRight() shouldBe Action.AssignIssue(issueNumber, user2)
    }

    @Test
    fun `happy path - AlreadyTaken`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user2, "I'm on it")
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue(assignee = user1).right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeRight() shouldBe Action.AlreadyTaken(user2, issueNumber, user1)
    }

    @Test
    fun `happy path - NotApproved`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user1, "I'm on it")
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("feature")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeRight() shouldBe Action.NotApproved(user1, issueNumber)
    }

    @Test
    fun `happy path - DoNothing - empty comments`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns emptyList<GitHubComment>().right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeRight() shouldBe Action.DoNothing(issueNumber)
    }

    @Test
    fun `happy path - DoNothing - ivy bot comment`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user1, "I'm on it"),
            GitHubComment(user2, "I'm on it"),
            GitHubComment(ivyBot, "Okay, I'm on it"),
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeRight() shouldBe Action.DoNothing(issueNumber)
    }

    @Test
    fun `happy path - DoNothing - unknown comment intention`() = testScope {
        // given
        coEvery {
            gitHubService.fetchIssueComments(issueNumber)
        } returns listOf(
            GitHubComment(user1, "Random comment")
        ).right()
        coEvery {
            gitHubService.fetchIssue(issueNumber)
        } returns newIssue().right()
        coEvery {
            gitHubService.fetchIssueLabels(issueNumber)
        } returns listOf(newLabel("approved")).right()

        // when
        val action = determineAction(args)

        // then
        action.shouldBeRight() shouldBe Action.DoNothing(issueNumber)
    }
}
