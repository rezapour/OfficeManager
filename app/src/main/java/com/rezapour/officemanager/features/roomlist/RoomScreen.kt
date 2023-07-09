package com.rezapour.officemanager.features.roomlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rezapour.officemanager.DataState
import com.rezapour.officemanager.R
import com.rezapour.officemanager.base.components.ErrorComponent
import com.rezapour.officemanager.base.components.Loading
import com.rezapour.officemanager.base.ui.theme.OfficeManagerTheme
import com.rezapour.officemanager.model.FactItem
import com.rezapour.officemanager.model.RoomItem
import kotlinx.coroutines.CoroutineScope


@Composable
fun RoomListScreen(
    viewModel: RoomListViewModel = hiltViewModel(),
    onNavigateToFilterScreen: () -> Unit,
    onNavigateToDetailScreen: () -> Unit

) {
    val uiState = viewModel.uiState.collectAsState().value
    val filterState = viewModel.filterState.collectAsState().value

    val coroutineScope: CoroutineScope = rememberCoroutineScope()


    Scaffold(topBar = {
        TopBar(
            filterState = filterState,
            onFilterClicked = onNavigateToFilterScreen
        )
    }) { paddingValues ->

        Content(
            Modifier.padding(paddingValues),
            uiState,
            onMoreClick = { roomItem ->
                viewModel.onMoreClicked(roomItem.lovooFact!!)
                onNavigateToDetailScreen()
            })
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    uiState: DataState<List<RoomItem>>,
    onMoreClick: (RoomItem) -> Unit
) {
    when (uiState) {
        is DataState.Error -> ErrorComponent(
            modifier = modifier.padding(
                start = dimensionResource(id = R.dimen.room_detail_padding),
                end = dimensionResource(
                    id = R.dimen.room_detail_padding
                )
            ), messageId = uiState.messageId
        )

        DataState.Loading -> Loading(modifier)
        is DataState.Success -> RoomList(
            modifier = modifier,
            roomList = uiState.data,
            onMoreClick = onMoreClick
        )
    }
}

@Composable
fun RoomList(
    modifier: Modifier = Modifier,
    roomList: List<RoomItem>,
    onMoreClick: (RoomItem) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = roomList) { roomItem ->
            RoomItem(roomItem = roomItem, onMoreClicked = onMoreClick)
        }


    }
}

@Preview
@Composable
fun RoomListPreview() {
    OfficeManagerTheme() {
        RoomList(
            roomList = getRoomList(),
            onMoreClick = {}
        )
    }
}

@Preview
@Composable
fun RoomScreenPreview() {
    RoomListScreen(onNavigateToDetailScreen = {}, onNavigateToFilterScreen = {})
}

fun getRoomList() = listOf(
    RoomItem(
        name = "Featureteam \"PRIME\"",
        department = "engineering",
        type = "team",
        id = "123",
        roomNumber = "4.2-08",
        officeLevel = 4,
        lovooFact = FactItem(
            images = listOf("1"),
            text = "Hello",
            title = "tiltle"
        )
    ), RoomItem(
        name = "Featureteam \"PRIME\"",
        department = "engineering",
        type = "team",
        id = "1232",
        roomNumber = "4.2-08",
        officeLevel = 4,
        lovooFact = FactItem(
            images = listOf("1"),
            text = "Hello",
            title = "tiltle"
        )
    )
)