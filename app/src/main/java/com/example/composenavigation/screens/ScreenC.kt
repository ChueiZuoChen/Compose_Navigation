package com.example.composenavigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScreenC(onNavigation: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Yellow
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "This is Screen C")
            Button(
                onClick = { onNavigation() },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "Go to Screen A")
            }
        }
    }
}