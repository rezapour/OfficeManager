package com.rezapour.officemanager.features.roomdetail

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rezapour.officemanager.R
import com.rezapour.officemanager.base.components.fontDimensionResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarDetail(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    onBackClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleMedium,
                fontSize = fontDimensionResource(id = R.dimen.topbar_text_title_size),
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = MaterialTheme.colorScheme.tertiary)
            }
        }
    )
}