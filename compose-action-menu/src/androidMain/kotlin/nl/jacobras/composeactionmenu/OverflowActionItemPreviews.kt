package nl.jacobras.composeactionmenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@PreviewUiComponent
@Composable
private fun RegularOverflowActionPreview() {
    OverflowActionItem(
        item = RegularActionItem(
            key = "search",
            title = "OK",
            iconVector = Icons.Filled.Search,
            onClick = {}
        ),
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}

@PreviewUiComponent
@Composable
private fun RegularOverflowActionWithoutIconPreview() {
    OverflowActionItem(
        item = RegularActionItem(
            key = "search",
            title = "OK",
            iconVector = null,
            onClick = {}
        ),
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}

@PreviewUiComponent
@Composable
private fun DisabledOverflowActionWithoutIconPreview() {
    OverflowActionItem(
        item = RegularActionItem(
            key = "search",
            title = "OK",
            iconVector = null,
            enabled = false,
            onClick = {}
        ),
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}

@PreviewUiComponent
@Composable
private fun CheckableOverflowActionPreview() {
    OverflowActionItem(
        item = CheckableActionItem(
            key = "search",
            title = "OK",
            iconVector = Icons.Filled.Search,
            isChecked = true,
            onClick = {}
        ),
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}

@PreviewUiComponent
@Composable
private fun RadioOverflowActionPreview() {
    OverflowActionItem(
        item = RadioActionItem(
            key = "search",
            title = "OK",
            iconVector = Icons.Filled.Search,
            isSelected = true,
            onClick = {}
        ),
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}

@PreviewUiComponent
@Composable
private fun GroupOverflowActionPreview() {
    OverflowActionItem(
        item = GroupActionItem(
            key = "search",
            title = "OK",
            iconVector = Icons.Filled.Search,
            childOptions = emptyList()
        ),
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}