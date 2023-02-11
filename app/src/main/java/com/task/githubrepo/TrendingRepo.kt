package com.task.githubrepo

import com.task.githubrepo.data.dtos.Item

class TrendingRepo(private val service: RepoService) : ITrendingRepo {
    override suspend fun fetchRepo(): List<Item>? {
        val response = service.getTrendingRepos()
        return if (response.isSuccessful) {
            response.body()?.items
        } else
            listOf()
    }
}