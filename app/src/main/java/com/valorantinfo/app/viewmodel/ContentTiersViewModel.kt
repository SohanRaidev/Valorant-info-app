package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.ContentTier
import com.valorantinfo.app.data.repository.ValorantRepository
import com.valorantinfo.app.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContentTiersViewModel(
    private val repository: ValorantRepository = ValorantRepository()
) : ViewModel() {

    private val _contentTiersState = MutableStateFlow<UiState<List<ContentTier>>>(UiState.Loading)
    val contentTiersState: StateFlow<UiState<List<ContentTier>>> = _contentTiersState.asStateFlow()

    init {
        loadContentTiers()
    }

    private fun loadContentTiers() {
        viewModelScope.launch {
            _contentTiersState.value = UiState.Loading
            repository.getContentTiers().collect { result ->
                _contentTiersState.value = result.fold(
                    onSuccess = { contentTiers -> UiState.Success(contentTiers) },
                    onFailure = { error -> UiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }

    fun retry() {
        loadContentTiers()
    }
}
