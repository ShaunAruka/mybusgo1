package com.shaun.mybusgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shaun.mybusgo.navigation.AppNavHost
import com.shaun.mybusgo.ui.theme.MyBusGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBusGoTheme {
                AppNavHost()
            }
        }
    }
}