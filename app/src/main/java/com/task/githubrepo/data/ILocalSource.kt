package com.task.githubrepo.data

import com.task.githubrepo.data.dtos.Repository

interface ILocalSource {
    fun getLocallySaveRepo(): Repository?
    fun saveRepoLocally(repo: Repository): Boolean
}
