package com.adeel.githubrepos.view.fragment


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adeel.githubrepos.R
import com.adeel.githubrepos.view.adapter.GithubRepoAdapter
import com.adeel.githubrepos.viewmodel.GithubRepoViewModel
import kotlinx.android.synthetic.main.fragment_github_repos.*

class GithubReposFragment : Fragment(R.layout.fragment_github_repos) {
    private lateinit var githubRepoViewModel: GithubRepoViewModel
    private val githubRepoAdapter by lazy { GithubRepoAdapter(ArrayList()) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        githubRepoViewModel = ViewModelProvider(activity!!)[GithubRepoViewModel::class.java]

        rvGithubRepos.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = githubRepoAdapter
        }

        githubRepoViewModel.fetchGithubRepos("language:kotlin", null, null)
        observeViewModel()
    }

    private fun observeViewModel() {
        githubRepoViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { response ->
            response.getContentIfNotHandled()?.let { isLoading ->
                if (isLoading) showLoading() else hideLoading()
            }
        })

        githubRepoViewModel.githubRepoResponse.observe(viewLifecycleOwner, Observer { response ->
            response.getContentIfNotHandled()?.let { githubRepoResponse ->
                githubRepoAdapter.updateRepo(githubRepoResponse.items)
            }
        })

        githubRepoViewModel.githubRepoResponse.observe(viewLifecycleOwner, Observer { response ->
            response.getContentIfNotHandled()?.let { error ->
                //Implement better error handling
                Toast.makeText(activity, "Some error occurred", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showLoading() {
        pbLoading.visibility = View.VISIBLE
        rvGithubRepos.visibility = View.GONE
    }

    private fun hideLoading() {
        pbLoading.visibility = View.GONE
        rvGithubRepos.visibility = View.VISIBLE
    }
}
