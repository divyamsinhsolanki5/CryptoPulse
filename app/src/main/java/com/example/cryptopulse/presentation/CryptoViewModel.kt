package com.example.cryptopulse.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptopulse.data.CryptoCoin
import com.example.cryptopulse.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UiState {
    object Loading : UiState
    data class Success(val coins: List<CryptoCoin>) : UiState
    data class Error(val message: String) : UiState
}

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val _rawCoins = MutableStateFlow<List<CryptoCoin>>(emptyList())
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val searchQuery = MutableStateFlow("")

    val uiState: StateFlow<UiState> = combine(_uiState, searchQuery, _rawCoins) { state, query, raw ->
        if (state is UiState.Success) {
            val filtered = if (query.isEmpty()) {
                raw
            } else {
                raw.filter {
                    it.name.contains(query, ignoreCase = true) ||
                            it.symbol.contains(query, ignoreCase = true)
                }
            }
            UiState.Success(filtered)
        } else {
            state
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    init {
        fetchCryptoData()
    }

    fun fetchCryptoData() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getCoins()
                .onSuccess { list ->
                    _rawCoins.value = list
                    _uiState.value = UiState.Success(list)
                }
                .onFailure { error ->
                    _uiState.value = UiState.Error(error.localizedMessage ?: "Something went wrong")
                }
        }
    }
}
