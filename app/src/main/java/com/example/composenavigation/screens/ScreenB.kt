package com.example.composenavigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScreenB(onNavigation:()->Unit) {
    Surface(
        color = Color.Blue,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "This is Screen B")
            Button(
                onClick = { onNavigation() },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "Go to Screen C")
            }
        }
    }
}