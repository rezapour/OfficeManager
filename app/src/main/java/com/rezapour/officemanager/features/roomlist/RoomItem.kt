package com.rezapour.officemanager.features.roomlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rezapour.officemanager.R
import com.rezapour.officemanager.base.components.fontDimensionResource
import com.rezapour.officemanager.features.model.FactItem
import com.rezapour.officemanager.features.model.RoomItem
import com.rezapour.officemanager.base.ui.theme.OfficeManagerTheme
import com.rezapour.officemanager.utils.capitalize

@Composable
fun RoomItem(modifier: Modifier = Modifier, roomItem: RoomItem, onMoreClicked: (RoomItem) -> Unit) {
    Surface(
        modifier = modifier.shadow(
            dimensionResource(id = R.dimen.room_item_shadow),
            shape = MaterialTheme.shapes.small
        ),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surface,
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.filter_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.room_item_spaceby)),
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_office),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.room_item_image_height))
            )

            Text(
                text = roomItem.name,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = fontDimensionResource(id = R.dimen.room_item_font_size),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.room_item_padding))
            )

            Text(
                text = "${roomItem.roomNumber}, ${roomItem.officeLevel}th floor",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.room_item_padding))
            )

            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.room_item_padding_top))
                    .padding(start = dimensionResource(id = R.dimen.room_item_padding))
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.room_item_spaceby)),
            ) {
                BoxLabel(label = roomItem.department)
                roomItem.type?.let { BoxLabel(label = it) }
            }
            roomItem.lovooFact?.let {
                Row(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.room_item_padding_top),
                            end = dimensionResource(
                                id = R.dimen.room_item_padding
                            )
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "See More ->",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.clickable { onMoreClicked(roomItem) }
                    )
                }
            }
        }
    }
}


@Composable
private fun BoxLabel(modifier: Modifier = Modifier, label: String) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        color = MaterialTheme.colorScheme.onSurface
    ) {
        Text(
            text = label.capitalize(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoomItemPreview() {
    OfficeManagerTheme {
        RoomItem(
            roomItem = RoomItem(
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
            ), onMoreClicked = {}
        )

    }
}

@Preview(showBackground = true)
@Composable
fun BoxLabelPreview() {
    OfficeManagerTheme() {
        BoxLabel(label = "engineering")
    }
}