package com.adeel.githubrepos.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adeel.githubrepos.model.Filter
import com.adeel.githubrepos.model.TrendingGithubRepository
import com.adeel.githubrepos.model.TrendingGithubRepositoryAPIService
import com.adeel.githubrepos.networking.BaseViewModel
import com.adeel.githubrepos.networking.Event

class GithubRepoViewModel : BaseViewModel() {
    private val githubAPIService = TrendingGithubRepositoryAPIService()

    val trendingGithubRepoResponse = MutableLiveData<Event<List<TrendingGithubRepository>>>()
    val languagesResponse = MutableLiveData<Event<List<Filter>>>()

    fun fetchGithubRepos(language: String?, since: String?) =
        getResponse(
            githubAPIService.getGithubRepo(language, since),
            trendingGithubRepoResponse
        )

    fun getLanguage() = getResponse(githubAPIService.getLanguages(), languagesResponse)

}