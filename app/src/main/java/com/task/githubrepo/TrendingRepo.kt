package com.task.githubrepo

import com.task.githubrepo.data.dtos.Item

class TrendingRepo(private val service: RepoService) : ITrendingRepo {
    override suspend fun fetchRepo(): List<Item>? {
        return service.getTrendingRepos().items
    }
}