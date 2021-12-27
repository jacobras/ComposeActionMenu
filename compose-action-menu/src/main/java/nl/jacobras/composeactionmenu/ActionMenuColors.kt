package nl.jacobras.composeactionmenu

import androidx.compose.ui.graphics.Color

interface ActionMenuColors {
    val backgroundColor: Color
    val iconTint: Color
}

data class DefaultActionMenuColors(
    override val backgroundColor: Color = Color.White,
    override val iconTint: Color = Color(135, 135, 135)
) : ActionMenuColors