package nl.jacobras.composeactionmenu

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

/**
 * Previews a UI component (like button or a row) in different font sizes & themes.
 */
@PreviewLargeFonts
@PreviewThemes
internal annotation class PreviewUiComponent

@Preview(
    name = "150%",
    fontScale = 1.5f,
    showBackground = true,
    backgroundColor = 0xfff
)
@Preview(
    name = "200%",
    fontScale = 2f,
    showBackground = true,
    backgroundColor = 0xfff
)
internal annotation class PreviewLargeFonts

@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xfff
)
@Preview(
    name = "Dark",
    showBackground = true,
    backgroundColor = 0x000,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
)
internal annotation class PreviewThemes