package com.adeel.githubrepos.model

import com.adeel.githubrepos.networking.Event
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {
    @GET("search/repositories")
    fun getRepos(
        @Query("q") qualifiers: String,
        @Query("sort") sortBy: String?,
        @Query("order") orderBy: String?
    ): Single<GithubRepo>
}