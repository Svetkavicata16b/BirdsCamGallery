import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.birdscamgallery.R
import com.example.birdscamgallery.ui.theme.BirdsCamGalleryTheme
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Files

enum class GalleryScreen() {
    LogIn,
    ImagesList,
    BirdImage,
    Error
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    var serverHost by rememberSaveable { mutableStateOf("192.168.2.224") }
    var serverPort by rememberSaveable { mutableStateOf("21") }
    var serverUsername by rememberSaveable { mutableStateOf("ivo") }
    var serverPassword by rememberSaveable { mutableStateOf("123") }
    var imageName by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf("") }
    var isOnList by rememberSaveable { mutableStateOf(false) }
    var listOfNames = remember { mutableStateListOf<String>("name1", "name2", "name3", "name4", "name5", "name6", "name7", "name8", "name9", "name10", "name11", "name12", "name13", "name14", "name15", "name16", "name17", "name18", "name19", "name20") }

    fun callGetFTPFiles(): Unit {
        try {
            var arrayOfNames = getFileNames(serverHost, serverPort, serverUsername, serverPassword)
            listOfNames.clear()
            listOfNames.addAll(arrayOfNames)
            listOfNames.sort()
            listOfNames.reverse()

            if (!isOnList) {
                navController.navigate(GalleryScreen.ImagesList.name)
            }
        } catch (e: Exception) {
            error = e.toString()
            navController.navigate(GalleryScreen.Error.name)
        }
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.birds_cam_gallery), fontSize = 24.sp) },
                navigationIcon = {
                    if (isOnList) {
                        IconButton(onClick = { callGetFTPFiles() }) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                },
                modifier = Modifier,
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = GalleryScreen.LogIn.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = GalleryScreen.LogIn.name) {
                isOnList = false
                LogInScreen(
                    host = serverHost,
                    port = serverPort,
                    username = serverUsername,
                    password = serverPassword,
                    onValueChangedHost = { serverHost = it },
                    onValueChangedPort = { serverPort = it },
                    onValueChangedUsername = { serverUsername = it },
                    onValueChangedPassword = { serverPassword = it },
                    onNextButtonClicked = { callGetFTPFiles() }
                )
            }
            composable(route = GalleryScreen.ImagesList.name) {
                isOnList = true
                callGetFTPFiles()
                ListScreen(
                    imageName = imageName,
                    names = listOfNames,
                    onClick = {
                        imageName = it
                        try {
                            var byteArrayOutputStream: ByteArrayOutputStream =
                                getFTPImage(
                                    host = serverHost,
                                    port = serverPort,
                                    username = serverUsername,
                                    password = serverPassword,
                                    imageName = imageName,
                                )
                            navController.navigate(GalleryScreen.BirdImage.name)
                        }
                        catch (e: Exception) {
                            error = e.toString()
                            navController.navigate(GalleryScreen.Error.name)
                        }
                    },
                    modifier = Modifier
                )
            }
            composable(route = GalleryScreen.BirdImage.name) {
                isOnList = false

                ImageScreen(
                    byteArrayOutputStream =
                        getFTPImage(
                            host = serverHost,
                            port = serverPort,
                            username = serverUsername,
                            password = serverPassword,
                            imageName = imageName,
                        )
                )
            }

            composable(route = GalleryScreen.Error.name) {
                ErrorScreen(error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryPreview() {
    BirdsCamGalleryTheme {
        GalleryApp()
    }
}