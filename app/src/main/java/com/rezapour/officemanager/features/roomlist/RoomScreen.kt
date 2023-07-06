package com.rezapour.officemanager.features.roomlist

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rezapour.officemanager.DataState
import com.rezapour.officemanager.model.FactItem
import com.rezapour.officemanager.model.RoomItem
import com.rezapour.officemanager.ui.theme.OfficeManagerTheme
import com.rezapour.officemanager.base.components.Loading


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomListScreen(
    viewModel: RoomListViewModel = hiltViewModel(),
    onNavigateToFilterScreen: () -> Unit,
    onNavigateToDetailScreen: () -> Unit

) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(topBar = { TopBar(filterState = false, onFilterClicked = {}) }) { paddingValues ->
        Content(Modifier.padding(paddingValues), uiState)
    }
}

@Composable
fun Content(modifier: Modifier = Modifier, uiState: DataState<List<RoomItem>>) {
    when (uiState) {
        is DataState.Error -> Log.d("RezaAPP", "${uiState.e.message}")
        DataState.Loading -> Loading(modifier)
        is DataState.Success -> RoomList(modifier = modifier, roomList = uiState.data)
    }
}

@Composable
fun RoomList(modifier: Modifier = Modifier, roomList: List<RoomItem>) {
    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = roomList) { roomItem ->
            RoomItem(roomItem = roomItem, onMoreClicked = {})
        }
    }
}

@Preview
@Composable
fun RoomListPreview() {
    OfficeManagerTheme() {
        RoomList(
            roomList = getRoomList()
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