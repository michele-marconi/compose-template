package com.spindox.composetemplate.ui

sealed interface ScreenUiState {
    data object Initial : ScreenUiState
    data object Loading : ScreenUiState
    data class Success(val msg: String) : ScreenUiState
    data class Error(val msg: String) : ScreenUiState
}
