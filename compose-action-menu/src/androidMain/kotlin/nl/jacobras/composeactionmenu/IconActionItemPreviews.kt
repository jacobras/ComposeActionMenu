package nl.jacobras.composeactionmenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@PreviewUiComponent
@Composable
private fun RegularIconActionPreview() {
    IconActionItem(
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
private fun BlueIconActionPreview() {
    IconActionItem(
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
private fun DisabledIconActionPreview() {
    IconActionItem(
        item = RegularActionItem(
            key = "search",
            title = "OK",
            iconVector = Icons.Filled.Search,
            enabled = false,
            onClick = {}
        ),
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}

@PreviewUiComponent
@Composable
private fun TextIconActionPreview() {
    IconActionItem(
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
private fun BlueTextIconActionPreview() {
    IconActionItem(
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
private fun DisabledTextIconActionPreview() {
    IconActionItem(
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