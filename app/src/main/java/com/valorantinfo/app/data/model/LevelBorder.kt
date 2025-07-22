package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class LevelBordersResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<LevelBorder>
)

data class LevelBorder(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("levelNumberAppearance") val levelNumberAppearance: String,
    @SerializedName("startingLevel") val startingLevel: Int,
    @SerializedName("levelBorderIcon") val levelBorderIcon: String?,
    @SerializedName("smallPlayerCardAppearance") val smallPlayerCardAppearance: String?,
    @SerializedName("assetPath") val assetPath: String?
)
