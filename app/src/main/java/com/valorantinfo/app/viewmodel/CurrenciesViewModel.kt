package com.valorantinfo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantinfo.app.data.model.Currency
import com.valorantinfo.app.data.repository.ValorantRepository
import com.valorantinfo.app.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CurrenciesViewModel(
    private val repository: ValorantRepository = ValorantRepository()
) : ViewModel() {

    private val _currenciesState = MutableStateFlow<UiState<List<Currency>>>(UiState.Loading)
    val currenciesState: StateFlow<UiState<List<Currency>>> = _currenciesState.asStateFlow()

    init {
        loadCurrencies()
    }

    private fun loadCurrencies() {
        viewModelScope.launch {
            _currenciesState.value = UiState.Loading
            repository.getCurrencies().collect { result ->
                _currenciesState.value = result.fold(
                    onSuccess = { currencies -> UiState.Success(currencies) },
                    onFailure = { error -> UiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }

    fun retry() {
        loadCurrencies()
    }
}
