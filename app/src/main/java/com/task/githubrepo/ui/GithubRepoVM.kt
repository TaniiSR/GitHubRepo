package com.task.githubrepo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.githubrepo.data.ITrendingRepo
import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.remote.base.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubRepoVM @Inject constructor(private val repository: ITrendingRepo) : ViewModel() {
    private val _trendingRepos: MutableLiveData<List<Item>> = MutableLiveData()
    val trendingRepos: LiveData<List<Item>> = _trendingRepos

    fun fetchTrendingRepos(refresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.fetchTrendingRepos(!refresh)) {
                is NetworkResult.Success -> {
                    _trendingRepos.postValue(response.data.items)
                }
                is NetworkResult.Error -> {
                    _trendingRepos.postValue(listOf())
                }
            }
        }
    }
}