package nl.jacobras.composeactionmenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
        colors = ActionMenuDefaults.colors()
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
        colors = ActionMenuDefaults.colors()
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
        colors = ActionMenuDefaults.colors()
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
        colors = ActionMenuDefaults.colors()
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
        colors = ActionMenuDefaults.colors()
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
        colors = ActionMenuDefaults.colors()
    )
}