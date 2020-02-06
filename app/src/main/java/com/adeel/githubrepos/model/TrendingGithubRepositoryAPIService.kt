package com.adeel.githubrepos.model

import com.adeel.githubrepos.networking.getRetrofitClient
import io.reactivex.Single

class TrendingGithubRepositoryAPIService {
    private val retrofit by lazy { getRetrofitClient().create(TrendingGithubRepositoryAPI::class.java) }

    fun getGithubRepo(
        language: String?,
        since: String?
    ): Single<List<TrendingGithubRepository>> = retrofit.getRepos(language, since, null)

    fun getLanguages(): Single<List<Filter>> = retrofit.getLanguages()
}