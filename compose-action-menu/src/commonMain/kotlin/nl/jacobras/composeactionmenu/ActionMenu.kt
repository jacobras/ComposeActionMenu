package nl.jacobras.composeactionmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * An action menu with support for:
 * - Icons;
 * - Selectable/checkable items;
 * - Nested sub menus;
 * - Automatic overflow for items that don't fit the specified maximum.
 *
 * This should be used inside the `actions` parameter of a top app bar:
 *
 * ```
 * TopAppBar(
 *     actions = {
 *         ActionMenu(items = toolbarActions)
 *     }
 * )
 * ```
 *
 * @param items The items to show in the menu.
 * @param maxNumberOfIcons Number of icons to show, including overflow menu icon (if needed).
 * @param colors Optional color configuration.
 * @see ActionItem for the possible types of action items.
 */
@Composable
fun ActionMenu(
    items: List<ActionItem>,
    maxNumberOfIcons: Int = 3,
    colors: ActionMenuColors = ActionMenuDefaults.colors()
) {
    if (items.isEmpty()) {
        return
    }

    var showOverflowMenu by remember { mutableStateOf(false) }
    var subMenuContent by remember { mutableStateOf<List<ActionItem>>(emptyList()) }

    // Determine which items can be shown as icons and which go in the overflow menu.
    // Wrapped in a remember block so this code only executes when the menu configuration changes.
    val (iconActions, overflowActions) = remember(items, maxNumberOfIcons) {
        splitItems(items, maxNumberOfIcons)
    }

    iconActions.forEach { item ->
        key(item.key) {
            IconActionItem(
                item = item,
                colors = colors,
                modifier = Modifier.testTag("ActionMenu#${item.key}"),
                showSubMenu = { subMenuContent = it },
                hideSubMenu = { subMenuContent = emptyList() }
            )
        }
    }

    if (overflowActions.isNotEmpty()) {
        IconButton(
            modifier = Modifier.testTag("ActionMenu#overflow"),
            onClick = { showOverflowMenu = true }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More actions",
                tint = colors.contentColor
            )
        }
    }

    DropdownMenu(
        modifier = Modifier
            .width(240.dp)
            .background(colors.dropdownContainerColor),
        offset = DpOffset(0.dp, (-10).dp),
        expanded = showOverflowMenu || subMenuContent.isNotEmpty(),
        onDismissRequest = { showOverflowMenu = false; subMenuContent = emptyList() }
    ) {
        val content = if (showOverflowMenu) overflowActions else subMenuContent

        content.forEach { item ->
            key(item.key) {
                OverflowActionItem(
                    item = item,
                    colors = colors,
                    modifier = Modifier.testTag("ActionMenu#${item.key}"),
                    hideTopMenu = { showOverflowMenu = false },
                    showSubMenu = { subMenuContent = it },
                    hideSubMenu = { subMenuContent = emptyList() }
                )
            }
        }
    }
}

/**
 * Splits the items into icon items and overflow items, based on the allowed [maxNumberOfIcons].
 */
private fun splitItems(items: List<ActionItem>, maxNumberOfIcons: Int): Pair<List<ActionItem>, List<ActionItem>> {
    val iconItems = mutableListOf<ActionItem>()
    val overflowItems = mutableListOf<ActionItem>()
    val reservedSpaces = if (isOverflowNeeded(items, maxNumberOfIcons)) 1 else 0

    var availableIconSpaces = maxNumberOfIcons
    items.forEach { item ->
        when (item.showAsAction) {
            ShowAsActionMode.ALWAYS -> {
                iconItems.add(item)
                availableIconSpaces--
            }

            ShowAsActionMode.IF_ROOM -> {
                if (availableIconSpaces > reservedSpaces) {
                    iconItems.add(item)
                    availableIconSpaces--
                } else {
                    overflowItems.add(item)
                }
            }

            ShowAsActionMode.NEVER -> {
                overflowItems.add(item)
            }
        }
    }
    return iconItems to overflowItems
}

private fun isOverflowNeeded(items: List<ActionItem>, maxNumberOfIcons: Int): Boolean {
    val neverCount = items.count { it.showAsAction == ShowAsActionMode.NEVER }
    val ifRoomCount = items.count { it.showAsAction == ShowAsActionMode.IF_ROOM }
    val alwaysCount = items.count { it.showAsAction == ShowAsActionMode.ALWAYS }
    val availableIfRoomSpace = maxNumberOfIcons - alwaysCount
    return neverCount > 0 || availableIfRoomSpace < ifRoomCount
}