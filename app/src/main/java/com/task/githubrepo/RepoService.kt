package com.task.githubrepo

import com.task.githubrepo.data.dtos.Repository

interface RepoService {
    fun getTrendingRepos(): Repository
}
