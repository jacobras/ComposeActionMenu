import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.jacobras.composeactionmenu.ActionMenu
import nl.jacobras.composeactionmenu.GroupActionItem
import nl.jacobras.composeactionmenu.RadioActionItem
import nl.jacobras.composeactionmenu.RegularActionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() {
    val defaultText = "Hello world"
    var activeText by remember { mutableStateOf(defaultText) }
    var contactMethodState by remember { mutableStateOf(1) }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Compose Action Menu web demo") },
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
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text("Hello world", fontSize = 36.sp)
                Text(activeText, fontSize = 18.sp)
                Text("Selected contact option: #$contactMethodState", fontSize = 16.sp)
            }
        }
    }
}