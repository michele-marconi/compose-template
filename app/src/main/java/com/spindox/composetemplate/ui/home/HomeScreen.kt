package com.spindox.composetemplate.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.spindox.composetemplate.R
import com.spindox.composetemplate.data.entity.Beer
import com.spindox.composetemplate.enums.ThemeAppearance
import com.spindox.composetemplate.ui.ScreenUiState
import com.spindox.composetemplate.ui.components.AlertDialogWithImage
import com.spindox.composetemplate.ui.components.ErrorItem
import com.spindox.composetemplate.ui.components.LoadingIndicator
import com.spindox.composetemplate.ui.components.SearchBar

@Composable
fun HomeScreen(
    uiState: ScreenUiState,
    onNavigateClick: (source: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val beers by viewModel.homeRepository.loadBeersFromDB().collectAsState(initial = emptyList())

    HomeScreenUI(
        uiState,
        onNavigateClick,
        beers,
        themeAppearance = { viewModel.setThemeAppearance(it) },
        loadData = { viewModel.loadData() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    uiState: ScreenUiState,
    onNavigateClick: (source: String) -> Unit,
    beers: List<Beer>,
    themeAppearance: (ThemeAppearance) -> Unit,
    loadData: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    var menuExpanded by remember { mutableStateOf(false) }
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = {
                            menuExpanded = false
                        },
                    ) {
                        ThemeAppearance.values().forEach {
                            DropdownMenuItem(
                                text = { Text(it.type) },
                                onClick = {
                                    menuExpanded = false
                                    themeAppearance.invoke(it)
                                })
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is ScreenUiState.Initial -> {}

                is ScreenUiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is ScreenUiState.Success -> {
                    HomeScreenContent(
                        modifier = Modifier.fillMaxSize(),
                        itemList = beers.sortedBy { it.name },
                        onNavigateClick = onNavigateClick
                    )
                }

                is ScreenUiState.Error -> {
                    ErrorItem(
                        text = uiState.msg,
                        modifier = Modifier.fillMaxSize(),
                        clickAction = { loadData.invoke() }
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    itemList: List<Beer> = emptyList(),
    onNavigateClick: (source: String) -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }
    val selectedBeer = remember { mutableStateOf<Beer?>(null) }
    val textToSearch = rememberSaveable { mutableStateOf("") }

    val filteredList = itemList.filter {
        it.name?.contains(textToSearch.value, ignoreCase = true) ?: false
    }

    AlertDialogWithImage(openDialog, selectedBeer)

    Column(modifier = modifier) {
        SearchBar(
            hintText = stringResource(R.string.search_a_beer_and_drink_it),
            textToSearch = textToSearch,
            filteredList = filteredList
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {

            items(filteredList) { item ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { onNavigateClick(item.id.toString()) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        SubcomposeAsyncImage(
                            loading = { CircularProgressIndicator() },
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .clickable {
                                    selectedBeer.value = item
                                    openDialog.value = true
                                }
                                .shadow(elevation = 10.dp, shape = CircleShape, clip = true)
                                .background(
                                    color = MaterialTheme.colorScheme.inversePrimary,
                                    shape = RoundedCornerShape(size = 50.dp)
                                ),
                            model = item.imageURL,
                            contentDescription = null
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = item.name ?: "",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = stringResource(R.string.ibu, item.ibu.toString()),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(text = item.brewersTips ?: "", fontStyle = FontStyle.Italic)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreviewSuccess() = HomeScreenUI(
    uiState = ScreenUiState.Success,
    onNavigateClick = {},
    beers = emptyList(),
    themeAppearance = {},
    loadData = {}
)

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreviewError() = HomeScreenUI(
    uiState = ScreenUiState.Error("Something went wrong"),
    onNavigateClick = {},
    beers = emptyList(),
    themeAppearance = {},
    loadData = {}
)

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreviewLoading() = HomeScreenUI(
    uiState = ScreenUiState.Loading,
    onNavigateClick = {},
    beers = emptyList(),
    themeAppearance = {},
    loadData = {}
)
