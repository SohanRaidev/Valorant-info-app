package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.Event
import com.valorantinfo.app.data.repository.ValorantRepository
import com.valorantinfo.app.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventsViewModel(
    private val repository: ValorantRepository = ValorantRepository()
) : ViewModel() {

    private val _eventsState = MutableStateFlow<UiState<List<Event>>>(UiState.Loading)
    val eventsState: StateFlow<UiState<List<Event>>> = _eventsState.asStateFlow()

    init {
        loadEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch {
            _eventsState.value = UiState.Loading
            repository.getEvents().collect { result ->
                _eventsState.value = result.fold(
                    onSuccess = { events -> UiState.Success(events) },
                    onFailure = { error -> UiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }

    fun retry() {
        loadEvents()
    }
}
