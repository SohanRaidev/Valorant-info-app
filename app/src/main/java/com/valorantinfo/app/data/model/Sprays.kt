package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class SpraysResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Spray>
)

data class Spray(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("category") val category: String?,
    @SerializedName("themeUuid") val themeUuid: String?,
    @SerializedName("isNullSpray") val isNullSpray: Boolean,
    @SerializedName("hideIfNotOwned") val hideIfNotOwned: Boolean,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("fullIcon") val fullIcon: String?,
    @SerializedName("fullTransparentIcon") val fullTransparentIcon: String?,
    @SerializedName("animationPng") val animationPng: String?,
    @SerializedName("animationGif") val animationGif: String?,
    @SerializedName("assetPath") val assetPath: String?,
    @SerializedName("levels") val levels: List<SprayLevel>?
)

data class SprayLevel(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("sprayLevel") val sprayLevel: Int,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("assetPath") val assetPath: String?
)
