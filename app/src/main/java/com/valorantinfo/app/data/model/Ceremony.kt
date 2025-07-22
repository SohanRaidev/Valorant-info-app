package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class CeremoniesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Ceremony>
)

data class Ceremony(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("assetPath") val assetPath: String?
)
