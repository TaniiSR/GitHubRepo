package com.task.githubrepo.data.local

import com.task.githubrepo.data.dtos.Repository

interface ILocalSource {
    fun getLocallySaveRepo(): Repository?
    fun saveRepoLocally(repo: Repository): Boolean
}
