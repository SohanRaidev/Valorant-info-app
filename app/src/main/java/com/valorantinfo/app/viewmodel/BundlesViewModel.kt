package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.Bundle
import com.valorantinfo.app.data.repository.ValorantRepository
import com.valorantinfo.app.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BundlesViewModel(
    private val repository: ValorantRepository = ValorantRepository()
) : ViewModel() {

    private val _bundlesState = MutableStateFlow<UiState<List<Bundle>>>(UiState.Loading)
    val bundlesState: StateFlow<UiState<List<Bundle>>> = _bundlesState.asStateFlow()

    init {
        loadBundles()
    }

    private fun loadBundles() {
        viewModelScope.launch {
            _bundlesState.value = UiState.Loading
            repository.getBundles().collect { result ->
                _bundlesState.value = result.fold(
                    onSuccess = { bundles -> UiState.Success(bundles) },
                    onFailure = { error -> UiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }

    fun retry() {
        loadBundles()
    }
}
