package nl.jacobras.composeactionmenu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun OverflowActionItem(
    item: ActionItem,
    colors: ActionMenuColors,
    modifier: Modifier = Modifier,
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
        enabled = item.enabled,
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val iconPainter = when {
                    item.iconVector != null -> rememberVectorPainter(item.iconVector)
                    item.icon != null -> item.icon
                    else -> null
                }
                if (iconPainter != null) {
                    Icon(
                        painter = iconPainter,
                        contentDescription = null,
                        tint = colors.contentColor
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                val textAlpha = if (item.enabled) 1.0f else 0.5f
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    text = item.title,
                    color = colors.contentColor.copy(alpha = textAlpha),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                when (item) {
                    is CheckableActionItem -> {
                        Checkbox(
                            checked = item.isChecked,
                            onCheckedChange = { onClick() }
                        )
                    }

                    is RadioActionItem -> {
                        RadioButton(selected = item.isSelected, onClick = { onClick() })
                    }

                    is GroupActionItem -> {
                        Icon(
                            painter = rememberVectorPainter(Icons.AutoMirrored.Filled.KeyboardArrowRight),
                            contentDescription = null,
                            tint = colors.contentColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    else -> Unit
                }
            }
        }
    )
}