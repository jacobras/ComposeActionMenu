package nl.jacobras.composeactionmenu.sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nl.jacobras.composeactionmenu.ActionMenu
import nl.jacobras.composeactionmenu.CheckableActionItem
import nl.jacobras.composeactionmenu.GroupActionItem
import nl.jacobras.composeactionmenu.RadioActionItem
import nl.jacobras.composeactionmenu.RegularActionItem
import nl.jacobras.composeactionmenu.ShowAsActionMode

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val useLocation = remember { mutableStateOf(false) }
            val contactMethod = remember { mutableIntStateOf(2) }
            val person = remember { mutableIntStateOf(1) }

            SampleTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_name)) },
                            actions = { ActionMenu(items = buildToolbarActions(useLocation, contactMethod, person)) }
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .padding(16.dp)
                    ) {
                        Text("Welcome to the sample app! Try out the menu options above.")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Use location (just some example text): ${useLocation.value}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Person selected: #${person.intValue}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Contact method selected: #${contactMethod.intValue}")
                    }
                }
            }
        }
    }

    @Composable
    private fun buildToolbarActions(
        useLocationState: MutableState<Boolean>,
        contactMethodState: MutableState<Int>,
        personState: MutableState<Int>
    ) = listOf(
        RegularActionItem(
            key = "regularItem",
            title = stringResource(R.string.regular_option),
            iconVector = Icons.Default.Info,
            onClick = ::onActionItemClick
        ),
        GroupActionItem(
            key = "groupItem",
            title = stringResource(R.string.radio_group),
            iconVector = Icons.Default.Person,
            childOptions = listOf(
                RadioActionItem(
                    key = "radio1",
                    title = stringResource(R.string.person_1),
                    isSelected = personState.value == 1,
                    onClick = { personState.value = 1 }
                ),
                RadioActionItem(
                    key = "radio2",
                    title = stringResource(R.string.person_2),
                    isSelected = personState.value == 2,
                    onClick = { personState.value = 2 }
                ),
                RadioActionItem(
                    key = "radio3",
                    title = stringResource(R.string.person_3),
                    isSelected = personState.value == 3,
                    onClick = { personState.value = 3 }
                ),
            )
        ),
        CheckableActionItem(
            key = "useLocation",
            title = stringResource(R.string.use_location),
            iconVector = Icons.Default.LocationOn,
            isChecked = useLocationState.value,
            onClick = { useLocationState.value = !useLocationState.value }
        ),
        GroupActionItem(
            key = "contactMethod",
            title = stringResource(R.string.contact_options),
            childOptions = listOf(
                RadioActionItem(
                    key = "contact1",
                    title = stringResource(R.string.contact_1),
                    iconVector = Icons.Default.Phone,
                    isSelected = contactMethodState.value == 1,
                    onClick = { contactMethodState.value = 1 }
                ),
                RadioActionItem(
                    key = "contact2",
                    title = stringResource(R.string.contact_2),
                    iconVector = Icons.Default.Email,
                    isSelected = contactMethodState.value == 2,
                    onClick = { contactMethodState.value = 2 }
                ),
                RadioActionItem(
                    key = "contact3",
                    title = stringResource(R.string.contact_3),
                    iconVector = Icons.Default.Home,
                    isSelected = contactMethodState.value == 3,
                    onClick = { contactMethodState.value = 3 }
                ),
            )
        ),
        RegularActionItem(
            key = "disabledExample",
            title = stringResource(R.string.disabled_example),
            showAsAction = ShowAsActionMode.NEVER,
            enabled = false,
            onClick = ::onActionItemClick
        ),
        RegularActionItem(
            key = "settings",
            title = stringResource(R.string.settings),
            showAsAction = ShowAsActionMode.NEVER,
            onClick = ::onActionItemClick
        )
    )

    private fun onActionItemClick(key: String) {
        Toast.makeText(this, "Clicked item with key $key", Toast.LENGTH_SHORT).show()
    }
}