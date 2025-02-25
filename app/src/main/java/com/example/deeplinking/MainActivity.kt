package com.example.deeplinking

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
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

class MainActivity : ComponentActivity() {
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
                    MainScreen(deepLinkUri.value)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {  //Correct override syntax
        super.onNewIntent(intent)
        setIntent(intent) // THIS IS CRUCIAL!

        handleIntentData(intent)
    }


    private fun handleIntentData(intent: Intent?) {
        val intentData: Uri? = intent?.data
        if (intentData != null) {
            deepLinkUri.value = intentData // Update the State
            Log.d("DeepLink", "Deep link found: ${intentData.toString()}")
        } else {
            Log.d("DeepLink", "No deep link found")
        }
    }
}

@Composable
fun MainScreen(deepLinkUri: Uri?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Deep Linking Example")
        if (deepLinkUri != null) {
            Text(text = "Deep Link: ${deepLinkUri.toString()}")

            // Extract parameters if needed
            val param1 = deepLinkUri.getQueryParameter("param1") //Safe Call
            if (param1 != null) {
                Text(text = "Param1: $param1")
            }
        } else {
            Text(text = "No Deep Link Received")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeepLinkingTheme {
        MainScreen(null)
    }
}