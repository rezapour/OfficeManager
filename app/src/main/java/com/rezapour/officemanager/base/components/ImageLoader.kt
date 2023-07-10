package com.rezapour.officemanager.base.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rezapour.officemanager.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageLoader(modifier: Modifier = Modifier, imageUrl: String) {
    GlideImage(
        model = imageUrl,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = modifier

    )
    {
        it.error(R.drawable.baseline_error_24)
    }
}