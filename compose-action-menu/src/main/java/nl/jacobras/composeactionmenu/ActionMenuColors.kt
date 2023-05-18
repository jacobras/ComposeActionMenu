package nl.jacobras.composeactionmenu

import androidx.compose.ui.graphics.Color

interface ActionMenuColors {
    val regularIconTint: Color
    val dropdownBackgroundColor: Color
    val dropdownIconTint: Color
}

data class DefaultActionMenuColors(
    override val regularIconTint: Color = Color.White,
    override val dropdownBackgroundColor: Color = Color.White,
    override val dropdownIconTint: Color = Color(135, 135, 135)
) : ActionMenuColors