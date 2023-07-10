package com.rezapour.officemanager.base.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.rezapour.officemanager.R

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    @StringRes messageId: Int,
    retryClicked: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier,
            text = stringResource(id = messageId),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary
        )

        Text(
            modifier = modifier
                .padding(top = dimensionResource(id = R.dimen.error_component_padding))
                .clickable { retryClicked() },
            text = stringResource(id = R.string.retry),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}