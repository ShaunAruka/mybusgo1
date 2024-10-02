package com.shaun.mybusgo.ui.theme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Booking : BottomNavItem("booking", "Booking", Icons.Default.ShoppingCart)
    object Profile : BottomNavItem("profile", "Profile", Icons.Default.AccountCircle)
    object Checkout : BottomNavItem("checkout", "Checkout", Icons.Default.Check)
}



