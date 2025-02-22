import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = errorMessage,
        modifier = modifier
    )
}