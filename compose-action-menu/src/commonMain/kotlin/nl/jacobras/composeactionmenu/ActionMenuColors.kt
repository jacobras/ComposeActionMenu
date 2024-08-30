package nl.jacobras.composeactionmenu

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ActionMenuColors(
    val contentColor: Color,
    val dropdownContainerColor: Color
)

object ActionMenuDefaults {

    @Composable
    fun colors(
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        dropdownContainerColor: Color = MaterialTheme.colorScheme.surfaceContainer
    ) = ActionMenuColors(
        contentColor = contentColor,
        dropdownContainerColor = dropdownContainerColor
    )
}