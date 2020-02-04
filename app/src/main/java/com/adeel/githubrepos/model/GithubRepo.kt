package com.adeel.githubrepos.model


import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var items: List<Item>,
    @SerializedName("total_count")
    var totalCount: Int
) {
    data class Item(
        @SerializedName("created_at")
        var createdAt: String,
        @SerializedName("default_branch")
        var defaultBranch: String,
        @SerializedName("description")
        var description: String,
        @SerializedName("fork")
        var fork: Boolean,
        @SerializedName("forks_count")
        var forksCount: Int,
        @SerializedName("full_name")
        var fullName: String,
        @SerializedName("homepage")
        var homepage: String,
        @SerializedName("html_url")
        var htmlUrl: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("language")
        var language: String,
        @SerializedName("master_branch")
        var masterBranch: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("node_id")
        var nodeId: String,
        @SerializedName("open_issues_count")
        var openIssuesCount: Int,
        @SerializedName("owner")
        var owner: Owner,
        @SerializedName("private")
        var `private`: Boolean,
        @SerializedName("pushed_at")
        var pushedAt: String,
        @SerializedName("score")
        var score: Double,
        @SerializedName("size")
        var size: Int,
        @SerializedName("stargazers_count")
        var stargazersCount: Int,
        @SerializedName("updated_at")
        var updatedAt: String,
        @SerializedName("url")
        var url: String,
        @SerializedName("watchers_count")
        var watchersCount: Int
    ) {
        data class Owner(
            @SerializedName("avatar_url")
            var avatarUrl: String,
            @SerializedName("gravatar_id")
            var gravatarId: String,
            @SerializedName("id")
            var id: Int,
            @SerializedName("login")
            var login: String,
            @SerializedName("node_id")
            var nodeId: String,
            @SerializedName("received_events_url")
            var receivedEventsUrl: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String
        )
    }
}