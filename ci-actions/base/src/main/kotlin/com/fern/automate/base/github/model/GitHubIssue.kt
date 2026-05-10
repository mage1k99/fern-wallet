package com.fern.automate.base.github.model

data class GitHubIssue(
    val number: GitHubIssueNumber,
    val creator: GitHubUser,
    val assignee: GitHubUser?
)
