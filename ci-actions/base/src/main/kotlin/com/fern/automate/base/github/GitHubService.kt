package com.fern.automate.base.github

import arrow.core.Either
import com.fern.automate.base.github.model.GitHubComment
import com.fern.automate.base.github.model.GitHubIssue
import com.fern.automate.base.github.model.GitHubIssueNumber
import com.fern.automate.base.github.model.GitHubLabel
import com.fern.automate.base.github.model.GitHubPAT
import com.fern.automate.base.github.model.GitHubUsername
import com.fern.automate.base.github.model.NotBlankTrimmedString

interface GitHubService {
    suspend fun fetchIssue(
        issueNumber: GitHubIssueNumber
    ): Either<Throwable, GitHubIssue>

    suspend fun fetchIssueLabels(
        issueNumber: GitHubIssueNumber
    ): Either<Throwable, List<GitHubLabel>>

    suspend fun fetchIssueComments(
        issueNumber: GitHubIssueNumber
    ): Either<Throwable, List<GitHubComment>>

    suspend fun commentIssue(
        pat: GitHubPAT,
        issueNumber: GitHubIssueNumber,
        text: NotBlankTrimmedString
    ): Either<Throwable, Unit>

    suspend fun assignIssue(
        pat: GitHubPAT,
        issueNumber: GitHubIssueNumber,
        assignee: GitHubUsername
    ): Either<Throwable, Unit>
}
