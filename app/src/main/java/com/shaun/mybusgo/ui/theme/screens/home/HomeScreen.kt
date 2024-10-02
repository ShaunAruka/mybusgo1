package com.shaun.mybusgo.ui.theme.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaun.mybusgo.navigation.ROUTE_PROFILE
import com.shaun.mybusgo.navigation.BottomNavigationBar
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = { paddingValues ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
                    .padding(paddingValues)
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "MYBUSGO!!",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Welcome to MyBusGo, a simplified booking app for the coach bus company MyBus!",
                    color = Color.LightGray,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W600
                )
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Below are the routes that are serviced by MyBusGo:",
                    color = Color.LightGray,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W600,
                    fontStyle = FontStyle.Italic,
                )
                Spacer(modifier = Modifier.height(60.dp))

                // Display the sorted list of routes
                val routes = listOf(
                    "Nairobi to Mombasa 7.00 am",
                    "Nairobi to Kisumu 8.00 am",
                    "Nairobi to Eldoret 8.30 am",
                    "Nairobi to Nakuru 9.00 am",
                    "Nairobi to Nakuru 2.00 pm",
                    "Nairobi to Mombasa 6.00 pm",
                    "Nairobi to Kisumu 9.30 pm",
                    "Nairobi to Eldoret 10.00 pm",
                )


                routes.forEach { route ->
                    Text(
                        text = route,
                        color = Color.LightGray,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                Button(
                    onClick = { navController.navigate(ROUTE_PROFILE) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(
                        text = "Proceed to create a booking profile",
                        color = Color.LightGray,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(120.dp))

                Text(
                    text = "BOOKING YOUR BUS JUST GOT EASIER",
                    modifier = Modifier.padding(start = 45.dp),
                    color = Color.LightGray,
                    fontSize = 35.sp,
                    fontFamily = FontFamily.Cursive
                )
            }
        }
    )
}

@Composable
@Preview
private fun HomePrev() {
    HomeScreen(rememberNavController())
}
