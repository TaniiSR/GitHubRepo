package com.task.githubrepo.data.dtos

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null
)
