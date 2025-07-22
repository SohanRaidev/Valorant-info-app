package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.GameMap
import com.valorantinfo.app.data.repository.ValorantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapsViewModel : ViewModel() {
    
    private val repository = ValorantRepository()
    
    private val _mapsState = MutableStateFlow<UiState<List<GameMap>>>(UiState.Loading)
    val mapsState: StateFlow<UiState<List<GameMap>>> = _mapsState.asStateFlow()
    
    init {
        fetchMaps()
    }
    
    fun fetchMaps() {
        viewModelScope.launch {
            _mapsState.value = UiState.Loading
            repository.getMaps().collect { result ->
                _mapsState.value = if (result.isSuccess) {
                    UiState.Success(result.getOrNull() ?: emptyList())
                } else {
                    UiState.Error(result.exceptionOrNull() ?: Exception("Unknown error"))
                }
            }
        }
    }
    
    fun retryFetch() {
        fetchMaps()
    }
}
