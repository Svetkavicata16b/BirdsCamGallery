import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream

@Composable
fun ImageScreen(
    byteArrayOutputStream: ByteArrayOutputStream,
    modifier: Modifier = Modifier
) {
    var byteArray: ByteArray = byteArrayOutputStream.toByteArray()
    var bitmapImage: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            bitmap = bitmapImage.asImageBitmap(),
            contentDescription = "ShowByteArray",
            modifier = modifier
        )
    }
}