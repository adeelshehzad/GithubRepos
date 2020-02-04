package com.adeel.githubrepos.model

import com.adeel.githubrepos.networking.Event
import com.adeel.githubrepos.networking.getRetrofitClient
import io.reactivex.Single

class GithubAPIService {
    private val retrofit by lazy { getRetrofitClient().create(GithubAPI::class.java) }

    fun getGithubRepo(
        qualifiers: String,
        sortBy: String?,
        orderBy: String?
    ): Single<GithubRepo> = retrofit.getRepos(qualifiers, sortBy, orderBy)
}