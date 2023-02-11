package com.task.githubrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        mViewBinding.lyErrorView.buttonRetry.setOnClickListener {
            viewModel.fetchTrendingRepos(true)
        }
    }

    private fun initViews() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mViewBinding.recyclerView.adapter = adapter
    }

    private fun viewState(viewState: GithubRepoVM.ViewState) {
        hideLoadingView()
        when (viewState) {
            is GithubRepoVM.ViewState.Loading -> showLoadingView()
            is GithubRepoVM.ViewState.Data -> {
                if (!(viewState.repos.isNullOrEmpty())) {
                    adapter.setList(viewState.repos)
                    showDataView(true)
                } else {
                    showDataView(false)
                }
            }

            is GithubRepoVM.ViewState.Failure -> {
                mViewBinding.recyclerView.visibility = View.GONE
                mViewBinding.lyErrorView.layoutError.visibility = View.VISIBLE
            }
        }
    }

    private fun hideLoadingView() {
        mViewBinding.lyLoadingView.shimmerFrameLayout.visibility = View.GONE
    }
    private fun showLoadingView() {
        mViewBinding.lyLoadingView.shimmerFrameLayout.visibility = View.VISIBLE
        mViewBinding.recyclerView.visibility = View.GONE
        mViewBinding.lyErrorView.layoutError.visibility = View.GONE
    }

    private fun showDataView(isListShow: Boolean) {
        mViewBinding.lyErrorView.layoutError.visibility = if (isListShow) View.GONE else View.VISIBLE
        mViewBinding.recyclerView.visibility = if (isListShow) View.VISIBLE else View.GONE
    }

    private fun addObservers() {
        viewModel.viewState.observe(this, ::viewState)
    }

}