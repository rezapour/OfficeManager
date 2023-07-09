package com.rezapour.officemanager.features.roomlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rezapour.officemanager.R
import com.rezapour.officemanager.base.components.fontDimensionResource
import com.rezapour.officemanager.base.ui.theme.OfficeManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    modifier: Modifier = Modifier,
    filterState: Boolean,
    onFilterClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.room_list_titel),
                style = MaterialTheme.typography.titleMedium,
                fontSize = fontDimensionResource(id = R.dimen.topbar_text_title_size),
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        actions = {
            TopBarActions(
                onFilterClicked = onFilterClicked,
                filterState = filterState,
                enableState = true,
                modifier = Modifier
            )
        })
}

@Composable
private fun TopBarActions(
    modifier: Modifier = Modifier,
    onFilterClicked: () -> Unit,
    filterState: Boolean,
    enableState: Boolean
) {
    IconButton(
        onClick = onFilterClicked,
        enabled = enableState,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.filter_2),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.topbar_action_icon)),
            tint = if (filterState) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview
@Composable
private fun topBarPreview() {
    OfficeManagerTheme() {
        TopBar(filterState = false, onFilterClicked = {})
    }
}

