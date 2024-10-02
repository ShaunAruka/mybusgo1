package com.shaun.mybusgo.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shaun.mybusgo.ui.theme.navigation.BottomNavItem

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Booking,
        BottomNavItem.Checkout,
    )

    NavigationBar(
        containerColor = Color.Blue
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title,
                    color = Color.White
                    ) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.LightGray
                )
            )
        }
    }
}


