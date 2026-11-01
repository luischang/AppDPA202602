package dev.lchang.appdpa.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lchang.appdpa.presentation.apifootball.ApiFootballScreen
import dev.lchang.appdpa.presentation.auth.LoginScreen
import dev.lchang.appdpa.presentation.auth.RegisterScreen
import dev.lchang.appdpa.presentation.home.HomeScreen
import dev.lchang.appdpa.presentation.permissions.GalleryPermissionsScreen
import dev.lchang.appdpa.presentation.realtime.FirestoreRealtimeScreen
import dev.lchang.appdpa.presentation.supabase.SupabaseStorageScreen

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login")
    {
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen()
            }
        }
        composable("permissions") {
            DrawerScaffold(navController) {
                GalleryPermissionsScreen()
            }
        }
        composable("realtime") {
            DrawerScaffold(navController) {
                FirestoreRealtimeScreen()
            }
        }
        composable("supabase") {
            DrawerScaffold(navController) {
                SupabaseStorageScreen()
            }
        }
        composable("apifootball") {
            DrawerScaffold(navController) {
                ApiFootballScreen()
            }
        }
    }

}
