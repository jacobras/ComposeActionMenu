package nl.jacobras.composeactionmenu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * The shared behaviour of all action items.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to any `onClick` callback.
 * @param titleResId The resource ID for the title of this item.
 * @param iconVector Optional icon of type [ImageVector].
 * @param iconDrawable Optional icon of type drawable resource.
 * @param showAsAction Specifies where this item should be placed in the action menu.
 */
sealed class ActionItem(
    val key: String,
    @StringRes val titleResId: Int,
    val iconVector: ImageVector?,
    @DrawableRes val iconDrawable: Int = 0,
    val showAsAction: ShowAsActionMode
)

/**
 * A regular action item. Will be shown as an icon if there's room for it by default.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to [onClick].
 * @param titleResId The resource ID for the title of this item.
 * @param iconVector Optional icon of type [ImageVector].
 * @param iconDrawable Optional icon of type drawable resource.
 * @param showAsAction Specifies where this item should be placed in the action menu.
 * @param onClick Will be called when this item is clicked, with [key] passed in as a parameter.
 */
class RegularActionItem(
    key: String,
    @StringRes titleResId: Int,
    iconVector: ImageVector? = null,
    @DrawableRes iconDrawable: Int = 0,
    showAsAction: ShowAsActionMode = ShowAsActionMode.IF_ROOM,
    val onClick: (key: String) -> Unit
) : ActionItem(key, titleResId, iconVector, iconDrawable, showAsAction)

/**
 * A multi-checkable action item. This will never be shown as an icon, because then the check box can not be shown.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to [onClick].
 * @param titleResId The resource ID for the title of this item.
 * @param iconVector Optional icon of type [ImageVector].
 * @param iconDrawable Optional icon of type drawable resource.
 * @param isChecked Whether the check box should be shown as checked.
 * @param onClick Will be called when this item is clicked, with [key] passed in as a parameter.
 */
class CheckableActionItem(
    key: String,
    @StringRes titleResId: Int,
    iconVector: ImageVector? = null,
    @DrawableRes iconDrawable: Int = 0,
    val isChecked: Boolean,
    val onClick: (key: String) -> Unit
) : ActionItem(key, titleResId, iconVector, iconDrawable, ShowAsActionMode.NEVER)

/**
 * A radio-selectable action item. This will never be shown as an icon, because then the radio button can not be shown.
 *
 * @param key A unique ID for this item. Will be passed as a parameter to [onClick].
 * @param titleResId The resource ID for the title of this item.
 * @param iconVector Optional icon of type [ImageVector].
 * @param iconDrawable Optional icon of type drawable resource.
 * @param isSelected Whether the radio button should be shown as selected.
 * @param onClick Will be called when this item is clicked, with [key] passed in as a parameter.
 */
class RadioActionItem(
    key: String,
    @StringRes titleResId: Int,
    iconVector: ImageVector? = null,
    @DrawableRes iconDrawable: Int = 0,
    val isSelected: Boolean,
    val onClick: (key: String) -> Unit
) : ActionItem(key, titleResId, iconVector, iconDrawable, ShowAsActionMode.NEVER)

/**
 * A action item that opens a sub group. Will be shown as an icon if there's room for it by default.
 * Clicking this item will open a sub menu with more options, so there's no `onClick` parameter.
 *
 * @param key A unique ID for this item.
 * @param titleResId The resource ID for the title of this item.
 * @param iconVector Optional icon of type [ImageVector].
 * @param iconDrawable Optional icon of type drawable resource.
 * @param showAsAction Specifies where this item should be placed in the action menu.
 * @param childOptions The content of the sub menu to show when this item is clicked.
 */
class GroupActionItem(
    key: String,
    @StringRes titleResId: Int,
    iconVector: ImageVector? = null,
    @DrawableRes iconDrawable: Int = 0,
    showAsAction: ShowAsActionMode = ShowAsActionMode.IF_ROOM,
    val childOptions: List<ActionItem>
) : ActionItem(key, titleResId, iconVector, iconDrawable, showAsAction)