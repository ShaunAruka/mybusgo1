package com.shaun.mybusgo.ui.theme.screens.profile



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaun.mybusgo.navigation.ROUTE_BOOKING
import com.shaun.mybusgo.navigation.BottomNavigationBar

@Composable
fun ProfileScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

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
                    text = "PROFILE SETUP",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(50.dp))

                // First Name Input
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name",
                        color = Color.White) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Last Name Input
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name",
                        color = Color.White) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Email Input
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email",
                        color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Phone Number Input
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number",
                        color = Color.White) },
                    placeholder = { Text("0712345678",
                        color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(60.dp))

                Button(
                    onClick = {  navController.navigate("booking/$firstName/$lastName") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Proceed to Book My Bus",
                        color = Color.LightGray,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(120.dp))


            }
        }
    )
}

@Composable
@Preview
fun ProfilePrev() {
    ProfileScreen(navController = rememberNavController())
}
