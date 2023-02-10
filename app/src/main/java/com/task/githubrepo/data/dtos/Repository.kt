package com.task.githubrepo.data.dtos

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,
    @SerializedName("items")
    val items: List<Item>? = null,
    @SerializedName("total_count")
    val totalCount: Int? = null
)
