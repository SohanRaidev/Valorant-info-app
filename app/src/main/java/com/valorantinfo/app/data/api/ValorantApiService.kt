package com.valorantinfo.app.data.api

import com.valorantinfo.app.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantApiService {
    
    @GET("agents")
    suspend fun getAgents(
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean = true
    ): Response<AgentsResponse>
    
    @GET("maps")
    suspend fun getMaps(): Response<MapsResponse>
    
    @GET("weapons")
    suspend fun getWeapons(): Response<WeaponsResponse>
    
    @GET("buddies")
    suspend fun getBuddies(): Response<BuddiesResponse>
    
    @GET("bundles")
    suspend fun getBundles(): Response<BundlesResponse>
    
    @GET("ceremonies")
    suspend fun getCeremonies(): Response<CeremoniesResponse>
    
    @GET("competitivetiers")
    suspend fun getCompetitiveTiers(): Response<CompetitiveTiersResponse>
    
    @GET("contenttiers")
    suspend fun getContentTiers(): Response<ContentTiersResponse>
    
    @GET("contracts")
    suspend fun getContracts(): Response<ContractsResponse>
    
    @GET("currencies")
    suspend fun getCurrencies(): Response<CurrenciesResponse>
    
    @GET("events")
    suspend fun getEvents(): Response<EventsResponse>
    
    @GET("gamemodes")
    suspend fun getGamemodes(): Response<GamemodesResponse>
    
    @GET("gear")
    suspend fun getGear(): Response<GearResponse>
    
    @GET("levelborders")
    suspend fun getLevelBorders(): Response<LevelBordersResponse>
    
    @GET("playercards")
    suspend fun getPlayerCards(): Response<PlayerCardsResponse>
    
    @GET("playertitles")
    suspend fun getPlayerTitles(): Response<PlayerTitlesResponse>
    
    @GET("seasons")
    suspend fun getSeasons(): Response<SeasonsResponse>
    
    @GET("sprays")
    suspend fun getSprays(): Response<SpraysResponse>
    
    @GET("themes")
    suspend fun getThemes(): Response<ThemesResponse>
}
