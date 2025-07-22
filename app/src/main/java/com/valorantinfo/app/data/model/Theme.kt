package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class ThemesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Theme>
)

data class Theme(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("storeFeaturedImage") val storeFeaturedImage: String?,
    @SerializedName("assetPath") val assetPath: String?
)
