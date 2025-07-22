package com.valorantinfo.app.data.repository

import com.valorantinfo.app.data.api.RetrofitInstance
import com.valorantinfo.app.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ValorantRepository {
    
    private val api = RetrofitInstance.api
    
    suspend fun getAgents(): Flow<Result<List<Agent>>> = flow {
        try {
            val response = api.getAgents(isPlayableCharacter = true)
            if (response.isSuccessful) {
                response.body()?.let { agentsResponse ->
                    emit(Result.success(agentsResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getMaps(): Flow<Result<List<GameMap>>> = flow {
        try {
            val response = api.getMaps()
            if (response.isSuccessful) {
                response.body()?.let { mapsResponse ->
                    emit(Result.success(mapsResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getWeapons(): Flow<Result<List<Weapon>>> = flow {
        try {
            val response = api.getWeapons()
            if (response.isSuccessful) {
                response.body()?.let { weaponsResponse ->
                    emit(Result.success(weaponsResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getBuddies(): Flow<Result<List<Buddy>>> = flow {
        try {
            val response = api.getBuddies()
            if (response.isSuccessful) {
                response.body()?.let { buddiesResponse ->
                    emit(Result.success(buddiesResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getBundles(): Flow<Result<List<Bundle>>> = flow {
        try {
            val response = api.getBundles()
            if (response.isSuccessful) {
                response.body()?.let { bundlesResponse ->
                    emit(Result.success(bundlesResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getCeremonies(): Flow<Result<List<Ceremony>>> = flow {
        try {
            val response = api.getCeremonies()
            if (response.isSuccessful) {
                response.body()?.let { ceremoniesResponse ->
                    emit(Result.success(ceremoniesResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getCompetitiveTiers(): Flow<Result<List<CompetitiveTier>>> = flow {
        try {
            val response = api.getCompetitiveTiers()
            if (response.isSuccessful) {
                response.body()?.let { competitiveTiersResponse ->
                    emit(Result.success(competitiveTiersResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getContentTiers(): Flow<Result<List<ContentTier>>> = flow {
        try {
            val response = api.getContentTiers()
            if (response.isSuccessful) {
                response.body()?.let { contentTiersResponse ->
                    emit(Result.success(contentTiersResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getContracts(): Flow<Result<List<Contract>>> = flow {
        try {
            val response = api.getContracts()
            if (response.isSuccessful) {
                response.body()?.let { contractsResponse ->
                    emit(Result.success(contractsResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getCurrencies(): Flow<Result<List<Currency>>> = flow {
        try {
            val response = api.getCurrencies()
            if (response.isSuccessful) {
                response.body()?.let { currenciesResponse ->
                    emit(Result.success(currenciesResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getEvents(): Flow<Result<List<Event>>> = flow {
        try {
            val response = api.getEvents()
            if (response.isSuccessful) {
                response.body()?.let { eventsResponse ->
                    emit(Result.success(eventsResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getGamemodes(): Flow<Result<List<Gamemode>>> = flow {
        try {
            val response = api.getGamemodes()
            if (response.isSuccessful) {
                response.body()?.let { gamemodesResponse ->
                    emit(Result.success(gamemodesResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getGear(): Flow<Result<List<Gear>>> = flow {
        try {
            val response = api.getGear()
            if (response.isSuccessful) {
                response.body()?.let { gearResponse ->
                    emit(Result.success(gearResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getLevelBorders(): Flow<Result<List<LevelBorder>>> = flow {
        try {
            val response = api.getLevelBorders()
            if (response.isSuccessful) {
                response.body()?.let { levelBordersResponse ->
                    emit(Result.success(levelBordersResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getPlayerCards(): Flow<Result<List<PlayerCard>>> = flow {
        try {
            val response = api.getPlayerCards()
            if (response.isSuccessful) {
                response.body()?.let { playerCardsResponse ->
                    emit(Result.success(playerCardsResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getPlayerTitles(): Flow<Result<List<PlayerTitle>>> = flow {
        try {
            val response = api.getPlayerTitles()
            if (response.isSuccessful) {
                response.body()?.let { playerTitlesResponse ->
                    emit(Result.success(playerTitlesResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getSeasons(): Flow<Result<List<Season>>> = flow {
        try {
            val response = api.getSeasons()
            if (response.isSuccessful) {
                response.body()?.let { seasonsResponse ->
                    emit(Result.success(seasonsResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getSprays(): Flow<Result<List<Spray>>> = flow {
        try {
            val response = api.getSprays()
            if (response.isSuccessful) {
                response.body()?.let { spraysResponse ->
                    emit(Result.success(spraysResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    suspend fun getThemes(): Flow<Result<List<Theme>>> = flow {
        try {
            val response = api.getThemes()
            if (response.isSuccessful) {
                response.body()?.let { themesResponse ->
                    emit(Result.success(themesResponse.data))
                } ?: emit(Result.failure(Exception("No data received")))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
