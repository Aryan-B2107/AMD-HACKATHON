// The package declaration is essential for the Android build system to correctly identify the file's location in your project.
package com.example.cep_agro_guider_frontend

// Import the necessary Jetpack Compose and Android components
import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cep_agro_guider_frontend.ui.theme.CEP_AGRO_GUIDER_FRONTENDTheme
import com.example.cep_agro_guider_frontend.ui.theme.CEP_AGRO_GUIDER_FRONTENDTheme

/**
 * Main activity for the Android application.
 * This class sets up the Jetpack Compose UI.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // The theme name is configured to match your project's theme.
            CEP_AGRO_GUIDER_FRONTENDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // The Composable that will display the WebView
                    HtmlWebView()
                }
            }
        }
    }
}

/**
 * A Composable function that creates and manages an Android WebView to display HTML content.
 */
@SuppressLint("SetJavaScriptEnabled") // Suppresses a lint warning for enabling JavaScript
@Composable
fun HtmlWebView() {
    // AndroidView is the key composable that allows you to embed a traditional Android View
    // within your Compose hierarchy.
    AndroidView(
        modifier = Modifier.fillMaxSize(), // Makes the WebView fill the available space
        factory = { context ->
            // This factory block creates and configures the WebView instance.
            WebView(context).apply {
                // Set a WebViewClient to handle page navigation within the WebView itself,
                // preventing the system from opening a new browser window.
                webViewClient = WebViewClient()

                // Access the WebView settings to enable JavaScript and viewport controls
                val webSettings: WebSettings = this.settings

                // Enable JavaScript, which is necessary for dynamic web content.
                webSettings.javaScriptEnabled = true
                webSettings.domStorageEnabled = true

                // This tells the WebView to support the viewport meta tag on the web page.
                webSettings.useWideViewPort = true

                // This makes the WebView load the entire content and zoom out to fit.
                webSettings.loadWithOverviewMode = true

                // **New changes for desktop mode and zoom controls**
                webSettings.setSupportZoom(true)
                webSettings.builtInZoomControls = true
                webSettings.displayZoomControls = false

                // This is the key line to make the WebView render as if it's a desktop browser.
                webSettings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36"

                // Load the HTML file from the 'assets' directory.
                // The 'file:///android_asset/' prefix is a special URI for Android assets.
                // This line points directly to the small_scale_form.html file.
                loadUrl("file:///android_asset/login.html")
            }
        },
        update = { webView ->
            // The update block is called when the composable is recomposed.
            // You can use this to update the WebView with new data if needed.
        }
    )
}

/**
 * Preview Composable for the WebView.
 */
@Preview(showBackground = true)
@Composable
fun WebViewPreview() {
    // The theme name is configured to match your project's theme.
    CEP_AGRO_GUIDER_FRONTENDTheme {
        HtmlWebView()
    }
}
