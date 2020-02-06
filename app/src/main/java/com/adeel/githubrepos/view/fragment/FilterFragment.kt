package com.adeel.githubrepos.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adeel.githubrepos.R
import com.adeel.githubrepos.model.Filter
import com.adeel.githubrepos.view.adapter.FilterAdapter
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : BaseFragment(R.layout.fragment_filter), View.OnClickListener {
    override fun onClick(v: View?) {
        v?.let { view ->
            val filter = view.tag as Filter?
            filter?.let {
                if (isLanguageFilter) {
                    filterSharingViewModel.filterLanguage.value = it
                } else {
                    filterSharingViewModel.filterTrendingSince.value = it
                }
            }

            Navigation.findNavController(view).navigate(R.id.actionOpenGithubRepoFragment)
        }
    }

    private var isLanguageFilter: Boolean = false
    private val filterList = ArrayList<Filter>()

    private val filterAdapter: FilterAdapter by lazy { FilterAdapter(this, filterList) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeAndApplyProperties()

        if (isLanguageFilter) {
            observeViewModel(rvFilter, pbFilterLoading)
            githubRepoViewModel.getLanguage()
        } else {
            filterAdapter.updateFilter(getTrendingSinceFilter())
        }
    }

    private fun initializeAndApplyProperties() {
        arguments?.let {
            isLanguageFilter = FilterFragmentArgs.fromBundle(it).isLanguageFilter
        }

        rvFilter.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = filterAdapter
        }
    }

    override fun observeViewModel(recyclerView: RecyclerView, progressBar: ProgressBar) {
        super.observeViewModel(recyclerView, progressBar)

        githubRepoViewModel.languagesResponse.observe(
            viewLifecycleOwner,
            Observer { response ->
                response.getContentIfNotHandled()?.let { languageResponse ->
                    filterAdapter.updateFilter(languageResponse)
                }
            })
    }

    private fun getTrendingSinceFilter(): List<Filter> {
        val trendingSinceFilter = ArrayList<Filter>()
        trendingSinceFilter.add(Filter("daily", "Trending Today", false))
        trendingSinceFilter.add(Filter("weekly", "Trending this week", false))
        trendingSinceFilter.add(Filter("monthly", "Trending this month", false))

        return trendingSinceFilter
    }
}
