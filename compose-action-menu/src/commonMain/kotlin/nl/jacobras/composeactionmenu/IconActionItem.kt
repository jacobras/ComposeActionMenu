package nl.jacobras.composeactionmenu

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
internal fun IconActionItem(
    modifier: Modifier = Modifier,
    item: ActionItem,
    tint: Color = DefaultActionMenuColors().regularIconTint,
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
            Icon(iconPainter, title, tint = tint)
        }
    } else {
        TextButton(modifier = modifier, onClick = onClick, enabled = item.enabled) {
            Text(
                text = title,
                color = tint.copy(alpha = LocalContentAlpha.current),
            )
        }
    }
}