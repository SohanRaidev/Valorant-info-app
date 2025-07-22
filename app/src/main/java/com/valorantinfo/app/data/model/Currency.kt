package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class CurrenciesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Currency>
)

data class Currency(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayNameSingular") val displayNameSingular: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("largeIcon") val largeIcon: String?,
    @SerializedName("assetPath") val assetPath: String?
)
