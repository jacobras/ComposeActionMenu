package nl.jacobras.composeactionmenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun RegularIconActionPreview() {
    IconActionItem(item = RegularActionItem(key = "search", title = "OK", iconVector = Icons.Filled.Search, onClick = {}))
}

@Preview
@Composable
private fun BlueIconActionPreview() {
    IconActionItem(item = RegularActionItem(key = "search", title = "OK", iconVector = Icons.Filled.Search, onClick = {}),
        tint = Color.Blue)
}

@Preview
@Composable
private fun DisabledIconActionPreview() {
    IconActionItem(item = RegularActionItem(key = "search", title = "OK", iconVector = Icons.Filled.Search, enabled = false, onClick = {}))
}

@Preview
@Composable
private fun TextIconActionPreview() {
    IconActionItem(item = RegularActionItem(key = "search", title = "OK", iconVector = null, onClick = {}))
}

@Preview
@Composable
private fun BlueTextIconActionPreview() {
    IconActionItem(item = RegularActionItem(key = "search", title = "OK", iconVector = null, onClick = {}), tint = Color.Blue)
}

@Preview
@Composable
private fun DisabledTextIconActionPreview() {
    IconActionItem(item = RegularActionItem(key = "search", title = "OK", iconVector = null, enabled = false, onClick = {}))
}