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

/**我們可以透過Lambda表達式讓MyNavHost集中管理跳轉頁面的操作*/
@Composable
fun ScreenA(onNavigation: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Red
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("This is screen A")
            Button(
                onClick = { onNavigation() }, /**當Button按下之後跳轉頁面*/
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "Go to Screen B")
            }
        }
    }
}