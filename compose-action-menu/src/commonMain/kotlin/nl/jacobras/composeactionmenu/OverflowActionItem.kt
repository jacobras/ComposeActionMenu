package nl.jacobras.composeactionmenu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun OverflowActionItem(
    modifier: Modifier = Modifier,
    item: ActionItem,
    colors: ActionMenuColors = DefaultActionMenuColors(),
    hideTopMenu: () -> Unit = {},
    showSubMenu: (items: List<ActionItem>) -> Unit = {},
    hideSubMenu: () -> Unit = {}
) {
    val onClick: () -> Unit = {
        when (item) {
            is GroupActionItem -> {
                hideTopMenu()
                showSubMenu(item.childOptions)
            }

            is CheckableActionItem -> {
                hideTopMenu()
                hideSubMenu()
                item.onClick(item.key)
            }

            is RadioActionItem -> {
                hideTopMenu()
                hideSubMenu()
                item.onClick(item.key)
            }

            is RegularActionItem -> {
                hideTopMenu()
                hideSubMenu()
                item.onClick(item.key)
            }
        }
    }

    DropdownMenuItem(
        modifier = modifier,
        contentPadding = PaddingValues(start = 8.dp),
        onClick = onClick,
        enabled = item.enabled
    ) {
        val iconPainter = when {
            item.iconVector != null -> rememberVectorPainter(item.iconVector)
            item.iconPainter != null -> item.iconPainter
            else -> null
        }
        if (iconPainter != null) {
            Icon(
                painter = iconPainter,
                tint = colors.dropdownIconTint,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        val textAlpha = if (item.enabled) 1.0f else 0.5f
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            text = item.title,
            color = MaterialTheme.colors.onBackground.copy(alpha = textAlpha),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        when (item) {
            is CheckableActionItem -> {
                Checkbox(checked = item.isChecked, onCheckedChange = { onClick() })
            }

            is RadioActionItem -> {
                RadioButton(selected = item.isSelected, onClick = { onClick() })
            }

            is GroupActionItem -> {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.OverflowRight),
                    tint = colors.dropdownIconTint,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            else -> Unit
        }
    }
}

@Preview
@Composable
private fun RegularOverflowActionPreview() {
    OverflowActionItem(item = RegularActionItem(
        key = "search",
        title = "OK",
        iconVector = Icons.Filled.Search,
        onClick = {}
    ))
}

@Preview
@Composable
private fun RegularOverflowActionWithoutIconPreview() {
    OverflowActionItem(item = RegularActionItem(
        key = "search",
        title = "OK",
        iconVector = null,
        onClick = {}
    ))
}

@Preview
@Composable
private fun DisabledOverflowActionWithoutIconPreview() {
    OverflowActionItem(item = RegularActionItem(
        key = "search",
        title = "OK",
        iconVector = null,
        enabled = false,
        onClick = {}
    ))
}

@Preview
@Composable
private fun CheckableOverflowActionPreview() {
    OverflowActionItem(item = CheckableActionItem(
        key = "search",
        title = "OK",
        iconVector = Icons.Filled.Search,
        isChecked = true,
        onClick = {}
    ))
}

@Preview
@Composable
private fun RadioOverflowActionPreview() {
    OverflowActionItem(item = RadioActionItem(
        key = "search",
        title = "OK",
        iconVector = Icons.Filled.Search,
        isSelected = true,
        onClick = {}
    ))
}

@Preview
@Composable
private fun GroupOverflowActionPreview() {
    OverflowActionItem(
        item = GroupActionItem(
            key = "search",
            title = "OK",
            iconVector = Icons.Filled.Search,
            childOptions = emptyList()
        )
    )
}