package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class PlayerTitlesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<PlayerTitle>
)

data class PlayerTitle(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String?,
    @SerializedName("titleText") val titleText: String?,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean,
    @SerializedName("assetPath") val assetPath: String?
)
