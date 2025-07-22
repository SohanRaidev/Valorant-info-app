package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class SeasonsResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Season>
)

data class Season(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("type") val type: String?,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("parentUuid") val parentUuid: String?,
    @SerializedName("assetPath") val assetPath: String?
)
