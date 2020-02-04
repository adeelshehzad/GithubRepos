package com.adeel.githubrepos.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adeel.githubrepos.model.GithubAPIService
import com.adeel.githubrepos.model.GithubRepo
import com.adeel.githubrepos.networking.BaseViewModel
import com.adeel.githubrepos.networking.Event

class GithubRepoViewModel : BaseViewModel() {
    private val githubAPIService = GithubAPIService()

    val githubRepoResponse = MutableLiveData<Event<GithubRepo>>()

    fun fetchGithubRepos(
        qualifiers: String,
        sortBy: String?,
        orderBy: String?
    ) {
        getResponse(githubAPIService.getGithubRepo(qualifiers, sortBy, orderBy), githubRepoResponse)
    }
}