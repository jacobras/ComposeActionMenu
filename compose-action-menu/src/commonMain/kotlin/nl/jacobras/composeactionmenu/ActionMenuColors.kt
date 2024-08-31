package nl.jacobras.composeactionmenu

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ActionMenuColors(
    val contentColor: Color,
    val overflowContainerColor: Color,
    val overflowContentColor: Color
)

object ActionMenuDefaults {

    @Composable
    fun colors(
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        overflowContainerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
        overflowContentColor: Color = MaterialTheme.colorScheme.onSurface,
    ) = ActionMenuColors(
        contentColor = contentColor,
        overflowContainerColor = overflowContainerColor,
        overflowContentColor = overflowContentColor
    )
}