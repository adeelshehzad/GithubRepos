package com.adeel.githubrepos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adeel.githubrepos.R
import com.adeel.githubrepos.databinding.ItemGithubRepoBinding
import com.adeel.githubrepos.model.TrendingGithubRepository

class TrendingGithubRepositoryAdapter(private val githubRepoList: ArrayList<TrendingGithubRepository>) :
    RecyclerView.Adapter<GithubRepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        val contentView = DataBindingUtil.inflate<ItemGithubRepoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_github_repo,
            parent,
            false
        )
        return GithubRepoViewHolder(contentView)
    }

    override fun getItemCount(): Int = githubRepoList.size

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        holder.view.githubrepo = githubRepoList[holder.adapterPosition]
    }

    fun updateRepo(updatedList: List<TrendingGithubRepository>) {
        githubRepoList.clear()
        githubRepoList.addAll(updatedList)
        notifyDataSetChanged()
    }
}

class GithubRepoViewHolder(val view: ItemGithubRepoBinding) : RecyclerView.ViewHolder(view.root)