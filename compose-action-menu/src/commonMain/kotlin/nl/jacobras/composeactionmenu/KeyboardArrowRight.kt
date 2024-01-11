package nl.jacobras.composeactionmenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Icons.Filled.OverflowRight: ImageVector
    get() {
        if (_overflowRight != null) {
            return _overflowRight!!
        }
        _overflowRight = materialIcon(name = "Filled.OverflowRight") {
            materialPath {
                // M10,17L15,12L10,7V17Z
                moveTo(10f, 17f)
                lineTo(15f, 12f)
                lineTo(10f, 7f)
                verticalLineTo(17f)
                close()
            }
        }
        return _overflowRight!!
    }

private var _overflowRight: ImageVector? = null