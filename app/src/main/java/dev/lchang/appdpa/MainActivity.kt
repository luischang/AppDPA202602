package dev.lchang.appdpa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.lchang.appdpa.presentation.auth.LoginScreen
import dev.lchang.appdpa.presentation.auth.RegisterScreen
import dev.lchang.appdpa.presentation.navigation.AppNavGraph
import dev.lchang.appdpa.ui.theme.AppDPATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDPATheme {
                AppNavGraph()
            }
        }
    }
}

