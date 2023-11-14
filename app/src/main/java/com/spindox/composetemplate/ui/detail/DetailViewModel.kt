package com.spindox.composetemplate.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.spindox.composetemplate.data.entity.Beer
import com.spindox.composetemplate.repository.DetailRepository
import com.spindox.composetemplate.ui.ScreenUiState
import com.spindox.composetemplate.utility.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val beerId: String = savedStateHandle.get<String>(Constants.SOURCE)!!
    var beerItem: Flow<Beer?> = loadBeerData(beerId)

    private fun loadBeerData(beerId: String) = flow {
        emit(detailRepository.loadBeerDetailFromDB(beerId).first())
    }
}
