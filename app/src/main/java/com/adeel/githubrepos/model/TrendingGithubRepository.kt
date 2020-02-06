package com.adeel.githubrepos.model


import com.google.gson.annotations.SerializedName

data class TrendingGithubRepository(
    @SerializedName("author")
    var author: String,
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("builtBy")
    var builtBy: List<BuiltBy>,
    @SerializedName("currentPeriodStars")
    var currentPeriodStars: Int,
    @SerializedName("description")
    var description: String,
    @SerializedName("forks")
    var forks: Int,
    @SerializedName("language")
    var language: String,
    @SerializedName("languageColor")
    var languageColor: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("stars")
    var stars: Int,
    @SerializedName("url")
    var url: String
) {
    data class BuiltBy(
        @SerializedName("avatar")
        var avatar: String,
        @SerializedName("href")
        var href: String,
        @SerializedName("username")
        var username: String
    )
}