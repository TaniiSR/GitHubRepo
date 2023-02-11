package com.task.githubrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.task.githubrepo.R
import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.databinding.ActivityGithubRepoBinding
import com.task.githubrepo.ui.adapter.TrendingAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GithubRepoActivity : AppCompatActivity() {
    private lateinit var mViewBinding : ActivityGithubRepoBinding
    private val viewModel: GithubRepoVM by viewModels()
    @Inject
    lateinit var adapter: TrendingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityGithubRepoBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initViews()
        setListener()
        addObservers()
        viewModel.fetchTrendingRepos()
    }

    private fun setListener() {
        mViewBinding.swRefresh.setOnRefreshListener {
            mViewBinding.swRefresh.isRefreshing = false
            viewModel.fetchTrendingRepos(true)
        }
    }

    private fun initViews() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mViewBinding.recyclerView.adapter = adapter
    }

    private fun listData(repos: List<Item>) {
        if (!(repos.isNullOrEmpty())) {
            adapter.setList(repos)
        } else {
        }
    }

    private fun addObservers() {
        viewModel.trendingRepos.observe(this, ::listData)
    }

}