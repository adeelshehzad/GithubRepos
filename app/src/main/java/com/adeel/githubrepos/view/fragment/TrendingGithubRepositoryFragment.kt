package com.adeel.githubrepos.view.fragment


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adeel.githubrepos.R
import com.adeel.githubrepos.view.adapter.TrendingGithubRepositoryAdapter
import kotlinx.android.synthetic.main.fragment_trending_github_repository.*

class TrendingGithubRepositoryFragment :
    BaseFragment(R.layout.fragment_trending_github_repository) {
    private val trendingGithubRepositoryAdapter by lazy { TrendingGithubRepositoryAdapter(ArrayList()) }

    private var filterLanguageKey = ""
    private var filterLanguageValue = "All language"

    private var filterSinceKey = "daily"
    private var filterSinceValue = "Trending Today"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setClickListeners()

        rvGithubRepos.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = trendingGithubRepositoryAdapter
        }

        observeViewModel(rvGithubRepos, pbGithubRepoLoading)
        githubRepoViewModel.fetchGithubRepos(filterLanguageKey, filterSinceKey)

        tvFilterLanguage.text = filterLanguageValue
        tvFilterSince.text = filterSinceValue
    }

    private fun setClickListeners() {
        tvFilterLanguage.setOnClickListener {
            navigateFragment(it, true)
        }

        tvFilterSince.setOnClickListener {
            navigateFragment(it, false)
        }
    }

    private fun navigateFragment(view: View, isLanguageFilter: Boolean) {
        val action =
            TrendingGithubRepositoryFragmentDirections.actionOpenFilterFragment(isLanguageFilter)
        Navigation.findNavController(view).navigate(action)
    }

    override fun observeViewModel(recyclerView: RecyclerView, progressBar: ProgressBar) {
        super.observeViewModel(recyclerView, progressBar)

        githubRepoViewModel.trendingGithubRepoResponse.observe(
            viewLifecycleOwner,
            Observer { response ->
                response.getContentIfNotHandled()?.let { githubRepoResponse ->
                    trendingGithubRepositoryAdapter.updateRepo(githubRepoResponse)
                }
            })

        filterSharingViewModel.filterLanguage.observe(activity!!, Observer { filter ->
            filterLanguageKey = filter.filterKey
            filterLanguageValue = filter.filterName
        })

        filterSharingViewModel.filterTrendingSince.observe(activity!!, Observer { filter ->
            filterSinceKey = filter.filterKey
            filterSinceValue = filter.filterName
        })
    }
}
