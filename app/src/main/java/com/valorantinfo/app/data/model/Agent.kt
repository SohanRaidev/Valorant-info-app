package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class AgentsResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Agent>
)

data class Agent(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("developerName") val developerName: String,
    @SerializedName("characterTags") val characterTags: List<String>?,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("displayIconSmall") val displayIconSmall: String?,
    @SerializedName("bustPortrait") val bustPortrait: String?,
    @SerializedName("fullPortrait") val fullPortrait: String?,
    @SerializedName("fullPortraitV2") val fullPortraitV2: String?,
    @SerializedName("killfeedPortrait") val killfeedPortrait: String?,
    @SerializedName("background") val background: String?,
    @SerializedName("backgroundGradientColors") val backgroundGradientColors: List<String>?,
    @SerializedName("assetPath") val assetPath: String?,
    @SerializedName("isFullPortraitRightFacing") val isFullPortraitRightFacing: Boolean,
    @SerializedName("isPlayableCharacter") val isPlayableCharacter: Boolean,
    @SerializedName("isAvailableForTest") val isAvailableForTest: Boolean,
    @SerializedName("isBaseContent") val isBaseContent: Boolean,
    @SerializedName("role") val role: Role?,
    @SerializedName("abilities") val abilities: List<Ability>
)

data class Role(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("assetPath") val assetPath: String?
)

data class Ability(
    @SerializedName("slot") val slot: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayIcon") val displayIcon: String?
)
