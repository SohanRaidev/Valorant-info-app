package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.Agent
import com.valorantinfo.app.data.repository.ValorantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val exception: Throwable) : UiState<Nothing>()
}

class AgentsViewModel : ViewModel() {
    
    private val repository = ValorantRepository()
    
    private val _agentsState = MutableStateFlow<UiState<List<Agent>>>(UiState.Loading)
    val agentsState: StateFlow<UiState<List<Agent>>> = _agentsState.asStateFlow()
    
    init {
        fetchAgents()
    }
    
    fun fetchAgents() {
        viewModelScope.launch {
            _agentsState.value = UiState.Loading
            repository.getAgents().collect { result ->
                _agentsState.value = if (result.isSuccess) {
                    UiState.Success(result.getOrNull() ?: emptyList())
                } else {
                    UiState.Error(result.exceptionOrNull() ?: Exception("Unknown error"))
                }
            }
        }
    }
    
    fun retryFetch() {
        fetchAgents()
    }
}
