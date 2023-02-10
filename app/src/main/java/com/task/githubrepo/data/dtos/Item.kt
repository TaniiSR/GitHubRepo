package com.task.githubrepo.data.dtos

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("stargazers_count")
    val stargazersCount: Int? = null,
    @SerializedName("owner")
    val owner: Owner? = null
)