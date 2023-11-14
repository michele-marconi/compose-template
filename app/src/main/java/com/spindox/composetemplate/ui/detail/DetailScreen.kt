package com.spindox.composetemplate.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.spindox.composetemplate.R
import com.spindox.composetemplate.data.entity.Beer
import com.spindox.composetemplate.data.entity.BoilVolume
import com.spindox.composetemplate.ui.components.VerticalSpacer

@Composable
fun DetailScreen(
    source: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val beerItem by viewModel.beerItem.collectAsState(initial = null)
    DetailScreenUI(source, onBackClick, beerItem)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenUI(source: String, onBackClick: () -> Unit, beerItem: Beer?) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.beer_detail).format(beerItem?.name)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            DetailScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                beerItem = beerItem
            )
        }
    }
}

@Composable
private fun DetailScreenContent(
    modifier: Modifier = Modifier,
    beerItem: Beer?
) {

    Card(
        modifier, elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                SubcomposeAsyncImage(
                    loading = { CircularProgressIndicator() },
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center),
                    model = beerItem?.imageURL,
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = CircleShape
                        )
                        .align(Alignment.BottomEnd)
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                        text = beerItem?.abv?.toString() ?: "",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            VerticalSpacer(20)
            Text(
                text = beerItem?.description ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            VerticalSpacer(20)
            Text(
                text = beerItem?.tagline ?: "",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = beerItem?.firstBrewed ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            VerticalSpacer(20)
            Text(
                text = beerItem?.contributedBy ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    DetailScreenUI(
        source = "",
        onBackClick = {},
        beerItem = Beer(
            id = 1,
            name = "Buzz",
            tagline = "A Real Bitter Experience.",
            firstBrewed = "09/2007",
            description = "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
            imageURL = "https://images.punkapi.com/v2/keg.png",
            abv = 4.5,
            ibu = 60.0,
            targetFg = 1010.0,
            targetOg = 1044.0,
            ebc = 20.0,
            srm = 10.0,
            ph = 4.4,
            attenuationLevel = 75.0,
            volume = BoilVolume(value = 20.0, unit = "litres"),
            boilVolume = BoilVolume(value = 25.0, unit = "litres"),
            brewersTips = "The earthy and floral aromas from the hops can be overpowering. Drop a little Cascade in at the end of the boil to lift the profile with a bit of citrus.",
            contributedBy = "Sam Mason <samjbmason>"
        )
    )
}
