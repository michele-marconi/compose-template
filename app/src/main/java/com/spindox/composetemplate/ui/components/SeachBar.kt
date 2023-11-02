package com.spindox.composetemplate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.spindox.composetemplate.R
import com.spindox.composetemplate.data.entity.Beer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(hintText: String, textToSearch: MutableState<String>, filteredList: List<Beer>) {
    var active by rememberSaveable { mutableStateOf(false) }

    androidx.compose.material3.SearchBar(
        modifier = Modifier.padding(8.dp),
        query = textToSearch.value,
        onQueryChange = { textToSearch.value = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text(hintText) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (textToSearch.value.isNotEmpty()) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable { textToSearch.value = "" })
            }
        }
    ) {
        // Show the first 5 items from the filtered list
        if (textToSearch.value.isNotEmpty()) {
            filteredList.take(5).forEach { beer ->
                ListItem(
                    headlineContent = { Text(beer.name ?: "") },
                    supportingContent = {
                        Text(
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            text = beer.description ?: ""
                        )
                    },
                    leadingContent = {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .clickable {
                            textToSearch.value = beer.name ?: ""
                            active = false
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    text = stringResource(R.string.no_elements_found),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}