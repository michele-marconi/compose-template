package com.spindox.composetemplate.ui

sealed interface ScreenUiState {
    data object Initial : ScreenUiState
    data object Loading : ScreenUiState
    data object Success : ScreenUiState
    data class Error(val msg: String) : ScreenUiState
}
