package nl.jacobras.composeactionmenu

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun IconActionItem(
    modifier: Modifier = Modifier,
    item: ActionItem,
    showSubMenu: (items: List<ActionItem>) -> Unit = {},
    hideSubMenu: () -> Unit = {}
) {
    val title = stringResource(item.titleResId)
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
        item.iconDrawable != 0 -> painterResource(id = item.iconDrawable)
        else -> null
    }

    if (iconPainter != null) {
        IconButton(modifier = modifier, onClick = onClick) {
            Icon(iconPainter, title, tint = MaterialTheme.colors.onPrimary)
        }
    } else {
        TextButton(modifier = modifier, onClick = onClick) {
            Text(
                text = title,
                color = MaterialTheme.colors.onPrimary.copy(alpha = LocalContentAlpha.current),
            )
        }
    }
}

@Preview
@Composable
private fun RegularIconActionPreview() {
    IconActionItem(item = RegularActionItem(
        key = "search",
        titleResId = android.R.string.ok,
        iconVector = Icons.Filled.Search,
        onClick = {}
    ))
}

@Preview
@Composable
private fun TextIconActionPreview() {
    IconActionItem(item = RegularActionItem(
        key = "search",
        titleResId = android.R.string.ok,
        iconVector = null,
        onClick = {}
    ))
}