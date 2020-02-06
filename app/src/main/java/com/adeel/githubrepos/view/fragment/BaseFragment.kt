package com.adeel.githubrepos.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.adeel.githubrepos.viewmodel.FilterSharingViewModel
import com.adeel.githubrepos.viewmodel.GithubRepoViewModel

open class BaseFragment(@LayoutRes layoutID: Int) : Fragment(layoutID) {
    lateinit var githubRepoViewModel: GithubRepoViewModel
    lateinit var filterSharingViewModel: FilterSharingViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        githubRepoViewModel = ViewModelProvider(this)[GithubRepoViewModel::class.java]
        filterSharingViewModel = ViewModelProvider(activity!!)[FilterSharingViewModel::class.java]
    }

    open fun observeViewModel(recyclerView: RecyclerView, progressBar: ProgressBar) {
        githubRepoViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { response ->
            response.getContentIfNotHandled()?.let { isLoading ->
                if (isLoading) showLoading(recyclerView, progressBar)
                else hideLoading(recyclerView, progressBar)
            }
        })

        githubRepoViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { response ->
            response.getContentIfNotHandled()?.let {
                //Implement better error handling
                Toast.makeText(activity, "Some error occurred", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showLoading(view: RecyclerView, loadingView: ProgressBar) {
        view.visibility = View.GONE
        loadingView.visibility = View.VISIBLE
    }

    private fun hideLoading(view: RecyclerView, loadingView: ProgressBar) {
        view.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
    }
}