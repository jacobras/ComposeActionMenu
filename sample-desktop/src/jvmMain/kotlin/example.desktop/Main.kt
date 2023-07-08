package example.desktop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import nl.jacobras.composeactionmenu.ActionMenu
import nl.jacobras.composeactionmenu.GroupActionItem
import nl.jacobras.composeactionmenu.RadioActionItem
import nl.jacobras.composeactionmenu.RegularActionItem

fun main() = application {
    val defaultText = "Hello world"
    var activeText by remember { mutableStateOf(defaultText) }
    var contactMethodState by remember { mutableStateOf(1) }

    Window(onCloseRequest = { exitApplication() }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Desktop sample") },
                    actions = {
                        ActionMenu(
                            maxNumberOfIcons = 2,
                            items = listOf(
                                RegularActionItem(
                                    key = "FAVOURITES",
                                    title = "Favourites",
                                    iconVector = Icons.Default.Star,
                                    onClick = { activeText = "Favourites" }
                                ),
                                RegularActionItem(
                                    key = "INFO",
                                    title = "Info",
                                    iconVector = Icons.Default.Info,
                                    onClick = { activeText = "Info" }
                                ),
                                GroupActionItem(
                                    key = "contactMethod",
                                    title = "Contact options",
                                    childOptions = listOf(
                                        RadioActionItem(
                                            key = "contact1",
                                            title = "Phone",
                                            iconVector = Icons.Default.Phone,
                                            isSelected = contactMethodState == 1,
                                            onClick = { contactMethodState = 1 }
                                        ),
                                        RadioActionItem(
                                            key = "contact2",
                                            title = "Email",
                                            iconVector = Icons.Default.Email,
                                            isSelected = contactMethodState == 2,
                                            onClick = { contactMethodState = 2 }
                                        ),
                                        RadioActionItem(
                                            key = "contact3",
                                            title = "Address",
                                            iconVector = Icons.Default.Home,
                                            isSelected = contactMethodState == 3,
                                            onClick = { contactMethodState = 3 }
                                        ),
                                    )
                                )
                            )
                        )
                    }
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                Text("Hello world", fontSize = 36.sp)
                Text(activeText, fontSize = 18.sp)
                Text("Selected contact option: #$contactMethodState", fontSize = 16.sp)
            }
        }
    }
}