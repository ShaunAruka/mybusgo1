package com.shaun.mybusgo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shaun.mybusgo.ui.theme.screens.booking.BookingScreen
import com.shaun.mybusgo.ui.theme.screens.checkout.CheckoutScreen
import com.shaun.mybusgo.ui.theme.screens.home.HomeScreen
import com.shaun.mybusgo.ui.theme.screens.profile.ProfileScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination: String = ROUTE_HOME) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(
            "booking/{firstName}/{lastName}",
            arguments = listOf(
                navArgument("firstName") { type = NavType.StringType },
                navArgument("lastName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            val lastName = backStackEntry.arguments?.getString("lastName") ?: ""

            BookingScreen(navController, firstName, lastName)
        }

        composable("checkout/{firstName}/{lastName/{route}/{date}/{time}") { backStackEntry ->
            val route = backStackEntry.arguments?.getString("route") ?: ""
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val time = backStackEntry.arguments?.getString("time") ?: ""
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            val lastName = backStackEntry.arguments?.getString("lastName") ?: ""
            CheckoutScreen(navController, route, date, time, firstName, lastName)
        }
        composable(ROUTE_PROFILE){
            ProfileScreen(navController)
        }
    }
}
