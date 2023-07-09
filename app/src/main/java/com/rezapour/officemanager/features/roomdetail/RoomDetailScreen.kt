package com.rezapour.officemanager.features.roomdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rezapour.officemanager.R
import com.rezapour.officemanager.model.FactItem
import com.rezapour.officemanager.base.components.ImageLoader
import com.rezapour.officemanager.base.ui.theme.OfficeManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    roomDetailViewModel: RoomDetailViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val fact = roomDetailViewModel.factState.collectAsState().value
    Scaffold(topBar = {
        TopBarDetail(onBackClicked = onBackClicked, title = R.string.room_detail_title)
    }) { paddingValues ->
        ContentItem(modifier = Modifier.padding(paddingValues), factItem = fact)
    }
}

@Composable
fun ContentItem(modifier: Modifier = Modifier, factItem: FactItem) {
    Column(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.room_detail_padding), end = dimensionResource(
                id = R.dimen.room_detail_padding
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.room_detail_padding))
    ) {
        ImageList(urls = factItem.images)
        Text(
            text = factItem.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = factItem.text,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}


@Composable
fun ImageList(modifier: Modifier = Modifier, urls: List<String>) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = urls) { url ->
            ImageItem(url = url)
        }
    }
}

@Composable
fun ImageItem(modifier: Modifier = Modifier, url: String) {
    ImageLoader(imageUrl = url, modifier = modifier.size(160.dp))
}

fun getFactItem() = FactItem(
    images = listOf(
        "https://firebasestorage.googleapis.com/v0/b/lv-trialwork.appspot.com/o/rooms%2FIMG_7.jpg?alt=media",
        "https://firebasestorage.googleapis.com/v0/b/lv-trialwork.appspot.com/o/rooms%2FIMG_8.jpg?alt=media",
        "https://firebasestorage.googleapis.com/v0/b/lv-trialwork.appspot.com/o/rooms%2FIMG_9.jpg?alt=media"
    ),
    title = "Customer care",
    text = "Making our users happy is the main challenge for our support team – every mail and message gets answered in a friendly manner while suggestions for improvement are evaluated together with product and data teams. Complaints and bug reports are reproduced and communicated to quality assurance. Our support continually improves in-app processes and analyses where we should communicate in a clearer way."
)

@Preview
@Composable
fun FactItemPreview() {
    OfficeManagerTheme() {
        ContentItem(factItem = getFactItem())
    }

}