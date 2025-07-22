package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class BundlesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Bundle>
)

data class Bundle(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayNameSubText") val displayNameSubText: String?,
    @SerializedName("description") val description: String,
    @SerializedName("extraDescription") val extraDescription: String?,
    @SerializedName("promoDescription") val promoDescription: String?,
    @SerializedName("useAdditionalContext") val useAdditionalContext: Boolean,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("displayIcon2") val displayIcon2: String?,
    @SerializedName("logoIcon") val logoIcon: String?,
    @SerializedName("verticalPromoImage") val verticalPromoImage: String?,
    @SerializedName("assetPath") val assetPath: String?
)
