package nl.jacobras.composeactionmenu

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * The shared behaviour of all action items.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to any `onClick` callback.
 * @param title The title of this item.
 * @param icon Optional icon (provide either this or [iconVector]).
 * @param iconVector Optional icon (provide either this or [icon]).
 * @param showAsAction Specifies where this item should be placed in the action menu.
 */
sealed class ActionItem(
    val key: String,
    val title: String,
    val icon: Painter?,
    val iconVector: ImageVector?,
    val showAsAction: ShowAsActionMode,
    val enabled: Boolean
)

/**
 * A regular action item. Will be shown as an icon if there's room for it by default.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to [onClick].
 * @param title The title of this item.
 * @param icon Optional icon (provide either this or [iconVector]).
 * @param iconVector Optional icon (provide either this or [icon]).
 * @param showAsAction Specifies where this item should be placed in the action menu.
 * @param onClick Will be called when this item is clicked, with [key] passed in as a parameter.
 */
class RegularActionItem(
    key: String,
    title: String,
    icon: Painter? = null,
    iconVector: ImageVector? = null,
    enabled: Boolean = true,
    showAsAction: ShowAsActionMode = ShowAsActionMode.IF_ROOM,
    val onClick: (key: String) -> Unit
) : ActionItem(key, title, icon, iconVector, showAsAction, enabled)

/**
 * A multi-checkable action item. This will never be shown as an icon, because then the check box can not be shown.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to [onClick].
 * @param title The title of this item.
 * @param icon Optional icon (provide either this or [iconVector]).
 * @param iconVector Optional icon (provide either this or [icon]).
 * @param isChecked Whether the check box should be shown as checked.
 * @param onClick Will be called when this item is clicked, with [key] passed in as a parameter.
 */
class CheckableActionItem(
    key: String,
    title: String,
    icon: Painter? = null,
    iconVector: ImageVector? = null,
    enabled: Boolean = true,
    val isChecked: Boolean,
    val onClick: (key: String) -> Unit
) : ActionItem(key, title, icon, iconVector, ShowAsActionMode.NEVER, enabled)

/**
 * A radio-selectable action item. This will never be shown as an icon, because then the radio button can not be shown.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to [onClick].
 * @param title The title of this item.
 * @param icon Optional icon (provide either this or [iconVector]).
 * @param iconVector Optional icon (provide either this or [icon]).
 * @param isSelected Whether the radio button should be shown as selected.
 * @param onClick Will be called when this item is clicked, with [key] passed in as a parameter.
 */
class RadioActionItem(
    key: String,
    title: String,
    icon: Painter? = null,
    iconVector: ImageVector? = null,
    enabled: Boolean = true,
    val isSelected: Boolean,
    val onClick: (key: String) -> Unit
) : ActionItem(key, title, icon, iconVector, ShowAsActionMode.NEVER, enabled)

/**
 * A action item that opens a sub group. Will be shown as an icon if there's room for it by default.
 * Clicking this item will open a sub menu with more options, so there's no `onClick` parameter.
 *
 * @param key A unique ID for this item.
 * @param title The title of this item.
 * @param icon Optional icon (provide either this or [iconVector]).
 * @param iconVector Optional icon (provide either this or [icon]).
 * @param showAsAction Specifies where this item should be placed in the action menu.
 * @param childOptions The content of the sub menu to show when this item is clicked.
 */
class GroupActionItem(
    key: String,
    title: String,
    icon: Painter? = null,
    iconVector: ImageVector? = null,
    enabled: Boolean = true,
    showAsAction: ShowAsActionMode = ShowAsActionMode.IF_ROOM,
    val childOptions: List<ActionItem>
) : ActionItem(key, title, icon, iconVector, showAsAction, enabled)