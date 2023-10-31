package com.spindox.composetemplate.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.spindox.composetemplate.data.entity.Beer
import kotlinx.coroutines.delay

/**
 * Create a [Spacer] of given width in [dp]
 */
@Composable
fun HorizontalSpacer(size: Int) = Spacer(modifier = Modifier.width(size.dp))

/**
 * Create a [Spacer] of given height in [dp]
 */
@Composable
fun VerticalSpacer(size: Int) = Spacer(modifier = Modifier.height(size.dp))

/**
 * Create a center aligned [CircularProgressIndicator] wrapped in a [Box]
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 4.dp
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size)
                .align(Alignment.Center),
            color = color,
            strokeWidth = strokeWidth
        )
    }
}

@Composable
fun ErrorItem(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.inversePrimary,
    clickAction: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                text = text,
                color = color
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { clickAction.invoke() }) {
                Text(text = "Retry")
            }
        }
    }
}

@Preview
@Composable
fun ErrorItemPreview() {
    ErrorItem(text = "Error preview", clickAction = {})
}

@Composable
fun AlertDialogWithImage(
    openDialog: MutableState<Boolean>,
    beer: MutableState<Beer?>
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        delay(500)
        visible = true
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center, text = beer.value?.name ?: ""
                )
            },
            text = {

                Column(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                    AnimatedVisibility(
                        visible = visible,
                        enter = slideInHorizontally { fullHeight -> fullHeight },
                    ) {
                        SubcomposeAsyncImage(
                            loading = { CircularProgressIndicator() },
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            model = beer.value?.imageURL,
                            contentDescription = null
                        )
                    }
                }

            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("Dismiss")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        visible = false
                    }) {
                    Text("Animate")
                }
            }
        )
    }
}


