package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.Weapon
import com.valorantinfo.app.data.repository.ValorantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeaponsViewModel : ViewModel() {
    
    private val repository = ValorantRepository()
    
    private val _weaponsState = MutableStateFlow<UiState<List<Weapon>>>(UiState.Loading)
    val weaponsState: StateFlow<UiState<List<Weapon>>> = _weaponsState.asStateFlow()
    
    init {
        fetchWeapons()
    }
    
    fun fetchWeapons() {
        viewModelScope.launch {
            _weaponsState.value = UiState.Loading
            repository.getWeapons().collect { result ->
                _weaponsState.value = if (result.isSuccess) {
                    UiState.Success(result.getOrNull() ?: emptyList())
                } else {
                    UiState.Error(result.exceptionOrNull() ?: Exception("Unknown error"))
                }
            }
        }
    }
    
    fun retryFetch() {
        fetchWeapons()
    }
}
