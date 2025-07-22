package com.valorantinfo.app.data.model

import com.google.gson.annotations.SerializedName

data class ContractsResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Contract>
)

data class Contract(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("shipIt") val shipIt: Boolean,
    @SerializedName("freeRewardScheduleUuid") val freeRewardScheduleUuid: String?,
    @SerializedName("content") val content: ContractContent?,
    @SerializedName("assetPath") val assetPath: String?
)

data class ContractContent(
    @SerializedName("relationType") val relationType: String?,
    @SerializedName("relationUuid") val relationUuid: String?,
    @SerializedName("chapters") val chapters: List<Chapter>?,
    @SerializedName("premiumRewardScheduleUuid") val premiumRewardScheduleUuid: String?,
    @SerializedName("premiumVPCost") val premiumVPCost: Int
)

data class Chapter(
    @SerializedName("isEpilogue") val isEpilogue: Boolean,
    @SerializedName("levels") val levels: List<ContractLevel>?,
    @SerializedName("freeRewards") val freeRewards: List<FreeReward>?
)

data class ContractLevel(
    @SerializedName("reward") val reward: Reward?,
    @SerializedName("xp") val xp: Int,
    @SerializedName("vpCost") val vpCost: Int,
    @SerializedName("isPurchasableWithVP") val isPurchasableWithVP: Boolean,
    @SerializedName("doughCost") val doughCost: Int,
    @SerializedName("isPurchasableWithDough") val isPurchasableWithDough: Boolean
)

data class FreeReward(
    @SerializedName("type") val type: String,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("isHighlighted") val isHighlighted: Boolean
)

data class Reward(
    @SerializedName("type") val type: String,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("isHighlighted") val isHighlighted: Boolean
)
