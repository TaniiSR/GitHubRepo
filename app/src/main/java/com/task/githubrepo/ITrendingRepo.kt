package com.task.githubrepo

import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository

interface ITrendingRepo {
    suspend fun fetchRepo(): NetworkResult<Repository>
}