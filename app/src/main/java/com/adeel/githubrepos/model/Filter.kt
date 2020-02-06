package com.adeel.githubrepos.model

import com.google.gson.annotations.SerializedName

data class Filter(
    @SerializedName("urlParam")
    val filterKey: String,
    @SerializedName("name")
    val filterName: String,
    val isChecked: Boolean
)