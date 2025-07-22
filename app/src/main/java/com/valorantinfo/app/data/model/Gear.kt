package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class GearResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Gear>
)

data class Gear(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("assetPath") val assetPath: String?,
    @SerializedName("shopData") val shopData: GearShopData?
)

data class GearShopData(
    @SerializedName("cost") val cost: Int,
    @SerializedName("category") val category: String,
    @SerializedName("categoryText") val categoryText: String,
    @SerializedName("gridPosition") val gridPosition: GridPosition?,
    @SerializedName("canBeTrashed") val canBeTrashed: Boolean,
    @SerializedName("image") val image: String?,
    @SerializedName("newImage") val newImage: String?,
    @SerializedName("newImage2") val newImage2: String?,
    @SerializedName("assetPath") val assetPath: String?
)
