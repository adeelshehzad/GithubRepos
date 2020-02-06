package com.adeel.githubrepos.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingGithubRepositoryAPI {
    @GET("repositories")
    fun  getRepos(
        @Query("language") language: String?,
        @Query("since") since: String?,
        @Query("spoken_language_code") spokenLanguageCode: String?
    ): Single<List<TrendingGithubRepository>>

    @GET("languages")
    fun getLanguages(): Single<List<Filter>>
}