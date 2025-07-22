package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class GamemodesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Gamemode>
)

data class Gamemode(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("economyType") val economyType: String?,
    @SerializedName("allowsMatchTimeouts") val allowsMatchTimeouts: Boolean,
    @SerializedName("isTeamVoiceAllowed") val isTeamVoiceAllowed: Boolean,
    @SerializedName("isMinimapHidden") val isMinimapHidden: Boolean,
    @SerializedName("orbCount") val orbCount: Int,
    @SerializedName("roundsPerHalf") val roundsPerHalf: Int,
    @SerializedName("teamRoles") val teamRoles: List<String>?,
    @SerializedName("gameFeatureOverrides") val gameFeatureOverrides: List<GameFeatureOverride>?,
    @SerializedName("gameRuleBoolOverrides") val gameRuleBoolOverrides: List<GameRuleBoolOverride>?,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("listViewIconTall") val listViewIconTall: String?,
    @SerializedName("assetPath") val assetPath: String?
)

data class GameFeatureOverride(
    @SerializedName("featureName") val featureName: String,
    @SerializedName("state") val state: Boolean
)

data class GameRuleBoolOverride(
    @SerializedName("ruleName") val ruleName: String,
    @SerializedName("state") val state: Boolean
)
