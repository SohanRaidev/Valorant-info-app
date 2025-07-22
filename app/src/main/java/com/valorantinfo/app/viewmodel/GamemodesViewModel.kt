package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.Gamemode
import com.valorantinfo.app.data.repository.ValorantRepository
import com.valorantinfo.app.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GamemodesViewModel(
    private val repository: ValorantRepository = ValorantRepository()
) : ViewModel() {

    private val _gamemodesState = MutableStateFlow<UiState<List<Gamemode>>>(UiState.Loading)
    val gamemodesState: StateFlow<UiState<List<Gamemode>>> = _gamemodesState.asStateFlow()

    init {
        loadGamemodes()
    }

    private fun loadGamemodes() {
        viewModelScope.launch {
            _gamemodesState.value = UiState.Loading
            repository.getGamemodes().collect { result ->
                _gamemodesState.value = result.fold(
                    onSuccess = { gamemodes -> UiState.Success(gamemodes) },
                    onFailure = { error -> UiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }

    fun retry() {
        loadGamemodes()
    }
}
