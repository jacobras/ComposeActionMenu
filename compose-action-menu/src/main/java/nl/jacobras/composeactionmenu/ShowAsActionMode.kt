package nl.jacobras.composeactionmenu

/**
 * Defines where to show an action item in the action menu.
 */
enum class ShowAsActionMode {

    /**
     * Always show this action item as an icon button. Layout issues (e.g. overlap of other UI elements)
     * might occur if there are multiple of these.
     */
    ALWAYS,

    /**
     * Only show this action item as an icon button if there's room for it.
     * If there is no room for this item it will be listed in the overflow menu.
     */
    IF_ROOM,

    /**
     * Never show this action item as an icon button. Instead, list it in the overflow menu.
     */
    NEVER
}