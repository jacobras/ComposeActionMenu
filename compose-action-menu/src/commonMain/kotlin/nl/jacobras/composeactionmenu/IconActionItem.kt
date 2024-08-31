package nl.jacobras.composeactionmenu

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
internal fun IconActionItem(
    item: ActionItem,
    contentColor: Color,
    modifier: Modifier = Modifier,
    showSubMenu: (items: List<ActionItem>) -> Unit = {},
    hideSubMenu: () -> Unit = {}
) {
    val title = item.title
    val onClick: () -> Unit = {
        when (item) {
            is GroupActionItem -> {
                showSubMenu(item.childOptions)
            }

            is CheckableActionItem -> {
                hideSubMenu()
                item.onClick(item.key)
            }

            is RadioActionItem -> {
                hideSubMenu()
                item.onClick(item.key)
            }

            is RegularActionItem -> {
                hideSubMenu()
                item.onClick(item.key)
            }
        }
    }
    val iconPainter = when {
        item.iconVector != null -> rememberVectorPainter(item.iconVector)
        item.icon != null -> item.icon
        else -> null
    }

    if (iconPainter != null) {
        IconButton(modifier = modifier, onClick = onClick, enabled = item.enabled) {
            Icon(iconPainter, title, tint = contentColor)
        }
    } else {
        TextButton(modifier = modifier, onClick = onClick, enabled = item.enabled) {
            Text(text = title, color = contentColor)
        }
    }
}