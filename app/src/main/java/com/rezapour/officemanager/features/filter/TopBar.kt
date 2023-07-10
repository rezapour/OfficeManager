package com.rezapour.officemanager.features.filter

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rezapour.officemanager.R
import com.rezapour.officemanager.base.components.fontDimensionResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarFilter(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    onBackClicked: () -> Unit,
    onClearFilter: () -> Unit
) {
    TopAppBar(
        modifier = modifier.padding(dimensionResource(id = R.dimen.top_bar_padding)),
        title = {
            Text(
                text = stringResource(R.string.room_filter_title),
                style = MaterialTheme.typography.titleMedium,
                fontSize = fontDimensionResource(id = R.dimen.topbar_text_title_size),
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }, actions = {
            Text(
                text = "Clear",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.labelLarge,
                modifier = modifier.clickable {
                    onClearFilter()
                }
            )
        }

    )
}

@Composable
private fun TopBarActions(
    modifier: Modifier = Modifier,
    onClearFilter: () -> Unit,
) {
    IconButton(
        onClick = onClearFilter,
        modifier = modifier.padding(0.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}