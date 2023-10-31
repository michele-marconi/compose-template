package com.spindox.composetemplate.ui.home

sealed interface HomeScreenUiState {
    data object Initial : HomeScreenUiState
    data object Loading : HomeScreenUiState
    data class Success(val msg: String) : HomeScreenUiState
    data class Error(val msg: String) : HomeScreenUiState
}
