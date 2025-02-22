package com.example.birdscamgallery

import GalleryApp
import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.birdscamgallery.ui.theme.BirdsCamGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // operations with Internet in main Thread
        var policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent {
            BirdsCamGalleryTheme {
                GalleryApp()
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