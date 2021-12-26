package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.composenavigation.screens.ScreenA
import com.example.composenavigation.screens.ScreenB
import com.example.composenavigation.screens.ScreenC
import com.example.composenavigation.ui.theme.ComposeNavigationTheme

object NavRoute {
    const val SCREEN_A = "ScreenA"
    const val SCREEN_B = "ScreenB"
    const val SCREEN_C = "ScreenC"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {

                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    //MyNavHost(navHostController = navController)
                    MyNavHost2(navHostController = navController)
                }
            }
        }
    }
}

/**傳遞參數*/
@Composable
fun MyNavHost2(navHostController: NavHostController) {
    /**因為Ｂ的接收有帶參數所以要重新建立標籤, “/” 後面是Key，他會創建一個Key名為myArg的bundle提供B去取得上一個頁面傳來的資料*/
    val routeWithArgument = "${NavRoute.SCREEN_B}/{myArg}"
    val routeWithArgumentC = "${NavRoute.SCREEN_C}/{visible}"
    NavHost(navController = navHostController, startDestination = NavRoute.SCREEN_A) {
        composable(NavRoute.SCREEN_A) {
            ScreenA {
                /**造一個value值*/
                val myString = "YoPotti"
                /**將它傳給ScreenB然後斜線後面是帶的內容*/
                navHostController.navigate("${NavRoute.SCREEN_B}/$myString")
            }
        }
        /**composable他有arguments參數是一個List*/
        composable(
            routeWithArgument,
            arguments = listOf(navArgument(name = "myArg") {})
        ) { navEntry ->
            /** navEntry是ScreenB的堆疊物件他裡面包含了所有資訊*/
            ScreenB(navEntry.arguments?.getString("myArg")) {
                /**傳一個Boolean值給ScreenC*/
                val myBoolean = true
                navHostController.navigate("${NavRoute.SCREEN_C}/$myBoolean")
            }
        }
        /**接收布林值得時候要注意如果是Null就必須設預設值，在arguments參數，必須使用NavType.BoolType去轉換型態給C接收*/
        composable(
            routeWithArgumentC,
            arguments = listOf(navArgument(name = "visible") {
                type = NavType.BoolType
            })
        ) {
            ScreenC(it.arguments?.getBoolean("visible", false)) {
                navHostController.navigate(NavRoute.SCREEN_A) {
                    popUpTo(NavRoute.SCREEN_A) {
                        inclusive = true
                    }
                }
            }
        }
    }
}


/**[1] 基本跳轉頁面
 * 創建navigation host 他需要三個參數
 * 1. navigation controller(控制畫面的跳轉動作)
 * 2. 再host的起始頁面,在上面我們定義了頁面的標籤,初始頁面設置ScreenA
 * 3. builder -> 建立host
 * */

/**[2]
 * 因為最後一個參數Builder是lambda表達式所以可直接不用寫，透過大括號去定義
 * NavHost(navController = navHostController, startDestination = NavRoute.SCREEN_A, builder = {})
 */
@Composable
fun MyNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoute.SCREEN_A
    ) {
        /**composable是把標籤跟 composable view關聯在一起*/
        composable(NavRoute.SCREEN_A) {
            ScreenA {
                /**將ScreenA觸發的事件跳轉到 標籤 "ScreenB"*/
                navHostController.navigate(NavRoute.SCREEN_B)
            }
        }
        composable(NavRoute.SCREEN_B) {
            /* ScreenB {
                 navHostController.navigate(NavRoute.SCREEN_C)
             }*/
        }
        composable(NavRoute.SCREEN_C) {
            /*ScreenC {
                navHostController.navigate(NavRoute.SCREEN_A) {
                    popUpTo(NavRoute.SCREEN_A) { inclusive = true }
                    */
            /** 一般情況下 A>B>C>A>B>C 他堆疊會一直累加 當返回會變成 C>B>A>C>B>A>跳出程式
             * 使用popUpTo() : A>B>C>A>B>C 當返回會變成 C>B>A>跳出程式
             * 他會pop堆疊直到A的出現就跳出程式.
             * popUpTo()底下的inclusive 式表示堆疊pop當到A時需要連同A一起pop嗎？
             * true : 到Ａ畫面時按返回會跳出程式
             * false : 到A畫面時按下第一次返回 不會有動作因為最後的堆疊A被清掉 然後再按第二次返回時，程式知道堆疊已經沒有東西了，所以跳出程式
             * *//*
                }
            }*/
        }
    }
}