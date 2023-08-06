import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.focusNextOnTab(focusManager: FocusManager) = onPreviewKeyEvent {
    if (it.key == Key.Tab && it.type == KeyEventType.KeyDown) {
        focusManager.moveFocus(FocusDirection.Next)
        true
    } else {
        false
    }
}


@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Press Tab to change focus")
            val focusManager = LocalFocusManager.current
            repeat(3) {
                var textValue by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = textValue,
                    onValueChange = {
                        textValue = it
                    },
                    modifier = Modifier.focusNextOnTab(focusManager)
                )
            }
        }
    }
}
