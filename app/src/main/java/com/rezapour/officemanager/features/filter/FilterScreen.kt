package com.rezapour.officemanager.features.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rezapour.officemanager.R
import com.rezapour.officemanager.model.FilterStatus
import com.rezapour.officemanager.model.SelectionOption
import com.rezapour.officemanager.base.ui.theme.OfficeManagerTheme
import com.rezapour.officemanager.utils.capitalize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(filterViewModel: FilterViewModel = hiltViewModel(), onBackClicked: () -> Unit) {
    val uiState = filterViewModel.filterState.collectAsState().value
    val departments = uiState.department.toMutableStateList()
    val types = uiState.type.toMutableStateList()
    Scaffold(
        topBar = {
            TopBarFilter(
                title = R.string.room_filter_title,
                onBackClicked = onBackClicked,
                onClearFilter = {
                    departments.forEach { it.selected = false }
                    types.forEach { it.selected = false }
                    filterViewModel.clearFilter()
                })
        }) { padding ->
        ContentSection(
            Modifier.padding(padding),
            department = departments,
            type = types,
            onDepartmentClick = { department ->

                departments.forEach { it.selected = false }
                departments.find { it.option == department.option }?.selected = true
            },
            onTypeClicked = { type ->
                types.forEach { it.selected = false }
                types.find { it.option == type.option }?.selected = true
            },
            onButtonClicked = {
                val department =
                    departments.firstOrNull() { department -> department.selected }?.option ?: ""
                val type = types.firstOrNull { type -> type.selected }?.option ?: ""
                if (department != "" || type != "")
                    filterViewModel.updateFilter(FilterStatus(department, type))
                onBackClicked()
            })
    }
}

@Composable
fun ContentSection(
    modifier: Modifier = Modifier,
    department: List<SelectionOption>,
    type: List<SelectionOption>,
    onDepartmentClick: (SelectionOption) -> Unit,
    onTypeClicked: (SelectionOption) -> Unit,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        FilterSection(label = "Department") {
            SelectionList(options = department, onOptionClicked = onDepartmentClick)
        }

        FilterSection(label = "Type") {
            SelectionList(options = type, onOptionClicked = onTypeClicked)
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onButtonClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Apply Filter")
        }
    }
}

@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
        content()
    }
}

@Composable
fun SelectionList(
    modifier: Modifier = Modifier,
    options: List<SelectionOption>,
    onOptionClicked: (SelectionOption) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.selection_space_by)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.selection_space_by))
    )
    {
        items(options) { option ->
            SelectionItem(
                selectionPotion = option,
                onOptionClicked = onOptionClicked
            )
        }
    }

}

@Composable
fun SelectionItem(
    modifier: Modifier = Modifier,
    selectionPotion: SelectionOption,
    onOptionClicked: (SelectionOption) -> Unit
) {
    Surface(
        modifier = modifier
            .clickable { onOptionClicked(selectionPotion) },
        color = if (selectionPotion.selected) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.onSurface
        },
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(
            text = selectionPotion.option.capitalize(),
            style = MaterialTheme.typography.titleSmall,
            color = if (selectionPotion.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.selection_text_padding)),
            textAlign = TextAlign.Center
        )
    }
}

private val _options = listOf(
    SelectionOption("option 1", false),
    SelectionOption("option 2", false),
    SelectionOption("option 3", false),
    SelectionOption("option 4", false),
    SelectionOption("option 5", false)
).toMutableStateList()
val options: List<SelectionOption>
    get() = _options

fun selectionOptionSelected(selectedOption: SelectionOption) {
    _options.forEach { it.selected = false }
    _options.find { it.option == selectedOption.option }?.selected = true
}

@Preview
@Composable
fun FilterLabelUnSelectedPreview() {
    OfficeManagerTheme {
        SelectionItem(selectionPotion = SelectionOption("engineering", false), onOptionClicked = {})
    }

}

@Preview
@Composable
fun FilterLabelSelectedPewView() {
    OfficeManagerTheme {
        SelectionItem(selectionPotion = SelectionOption("engineering", true), onOptionClicked = {})
    }
}

@Preview
@Composable
fun SelectionListPreview() {
    OfficeManagerTheme {
        SelectionList(options = _options, onOptionClicked = {})
    }
}