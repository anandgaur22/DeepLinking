package com.example.deeplinking

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.deeplinking.ui.theme.DeepLinkingTheme
import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

class MainActivity2 :  ComponentActivity() {
    private val deepLinkUri = mutableStateOf<Uri?>(null)  //Use livedata to trigger recomposition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Check for deep link from intent data
        handleIntentData(intent)

        setContent {
            DeepLinkingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen2(deepLinkUri.value)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntentData(intent)
    }


    private fun handleIntentData(intent: Intent?) {
        val intentData: Uri? = intent?.data
        if (intentData != null) {
            deepLinkUri.value = intentData
            Log.d("DeepLink", "Deep link found: ${intentData.toString()}")
        }
    }


    @Composable
    fun MainScreen2(deepLinkUri: Uri?) {
        val postId = deepLinkUri?.lastPathSegment  // Extract Post ID

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Dummy API Deep Linking Example")

            if (deepLinkUri != null) {
                Text(text = "Deep Link: ${deepLinkUri.toString()}")
                Text(text = "Post ID: $postId")

                // ✅ Corrected State Type
                val postState = remember { mutableStateOf<Post?>(null) }

                LaunchedEffect(postId) {
                    postId?.let {
                        postState.value = fetchPostDetails(it)  // API call
                    }
                }

                postState.value?.let { post ->
                    Text(text = "Title: ${post.title}")
                    Text(text = "Body: ${post.body}")
                }
            } else {
                Text(text = "No Deep Link Received")
            }
        }
    }


    @Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    DeepLinkingTheme {
        MainScreen2(null)
    }
}

}