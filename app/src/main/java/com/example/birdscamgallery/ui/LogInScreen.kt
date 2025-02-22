import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.birdscamgallery.R
import com.example.birdscamgallery.ui.theme.BirdsCamGalleryTheme

@Composable
fun LogInScreen(
    host: String,
    port: String,
    username: String,
    password: String,
    onValueChangedHost: (String) -> Unit,
    onValueChangedPort: (String) -> Unit,
    onValueChangedUsername: (String) -> Unit,
    onValueChangedPassword: (String) -> Unit,
    onNextButtonClicked: () -> Unit = {},
    modifier:Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = host,
            singleLine = true,
            modifier = Modifier,
            onValueChange = onValueChangedHost,
            label = { Text(stringResource(R.string.host)) }
        )
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            value = port,
            singleLine = true,
            modifier = Modifier,
            onValueChange = onValueChangedPort,
            label = { Text(stringResource(R.string.port)) }
        )
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            singleLine = true,
            modifier = Modifier,
            onValueChange = onValueChangedUsername,
            label = { Text(stringResource(R.string.username)) }
        )
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            singleLine = true,
            modifier = Modifier,
            onValueChange = onValueChangedPassword,
            label = { Text(stringResource(R.string.password)) }
        )
        Spacer(modifier = modifier.height(16.dp))
        Button(
            modifier = Modifier,
            onClick = onNextButtonClicked
        ) {
            Text(stringResource(R.string.next))
        }
    }
}