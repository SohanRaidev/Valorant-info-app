package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class PlayerCardsResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<PlayerCard>
)

data class PlayerCard(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean,
    @SerializedName("themeUuid") val themeUuid: String?,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("smallArt") val smallArt: String?,
    @SerializedName("wideArt") val wideArt: String?,
    @SerializedName("largeArt") val largeArt: String?,
    @SerializedName("assetPath") val assetPath: String?
)
