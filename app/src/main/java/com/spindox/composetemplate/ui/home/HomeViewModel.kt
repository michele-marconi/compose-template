package com.spindox.composetemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spindox.composetemplate.api.Resource
import com.spindox.composetemplate.data.datastore.DataStoreManager
import com.spindox.composetemplate.enums.ThemeAppearance
import com.spindox.composetemplate.repository.HomeRepository
import com.spindox.composetemplate.ui.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val homeRepository: HomeRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {
    private val _response = MutableStateFlow<ScreenUiState>(ScreenUiState.Initial)
    val response: StateFlow<ScreenUiState> = _response.asStateFlow()

    init {
        loadData()
    }

    fun setThemeAppearance(value: ThemeAppearance) = viewModelScope.launch(Dispatchers.IO) {
        dataStore.setThemeAppearance(value)
    }

    fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        _response.value = ScreenUiState.Loading
        when (val beersList = homeRepository.getBeersFromCloud()) {
            is Resource.Success -> {
                beersList.data?.let { homeRepository.insertBeersToDB(it) }
                _response.value = ScreenUiState.Success(msg = "yes")
            }

            is Resource.Error -> _response.value =
                if (homeRepository.loadBeersFromDB().first().isNotEmpty())
                    ScreenUiState.Success(msg = "yes")
                else
                    ScreenUiState.Error(msg = "Something went wrong")
        }
    }
}
