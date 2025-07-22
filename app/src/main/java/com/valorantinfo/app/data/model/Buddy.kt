package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class BuddiesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Buddy>
)

data class Buddy(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean,
    @SerializedName("themeUuid") val themeUuid: String?,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("assetPath") val assetPath: String?,
    @SerializedName("levels") val levels: List<BuddyLevel>?
)

data class BuddyLevel(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("charmLevel") val charmLevel: Int,
    @SerializedName("hideIfNotOwned") val hideIfNotOwned: Boolean,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("assetPath") val assetPath: String?
)
