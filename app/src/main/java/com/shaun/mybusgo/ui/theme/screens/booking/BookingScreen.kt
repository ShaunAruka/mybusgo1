package com.shaun.mybusgo.ui.theme.screens.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaun.mybusgo.navigation.BottomNavigationBar
import java.util.*

@Composable
fun BookingScreen(navController: NavHostController, firstName: String, lastName: String) {
    val context = LocalContext.current

    // User input states
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    val (route, setRoute) = remember { mutableStateOf(TextFieldValue("")) }

    val calendar = Calendar.getInstance()

    // Date Picker
    val dateDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Time Picker
    val timeDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    // Main Layout with Scaffold to hold Bottom Navigation
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = { paddingValues ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(16.dp)
                    .padding(paddingValues)
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Book Your Bus",
                    color = Color.Blue,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Route Input
                OutlinedTextField(
                    value = route,
                    onValueChange = { setRoute(it) },
                    label = { Text("Enter Route") },
                    placeholder = { Text("e.g., Nairobi - Mombasa") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Date Picker Button
                Button(onClick = { dateDialog.show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = if (selectedDate.isEmpty()) "Select Date" else "Date: $selectedDate")
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Time Picker Button
                Button(onClick = { timeDialog.show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = if (selectedTime.isEmpty()) "Select Time" else "Time: $selectedTime")
                }
                Spacer(modifier = Modifier.height(40.dp))

                // Confirm Button
                Button(
                    onClick = {
                        // Navigate to CheckoutScreen with selected values
                        navController.navigate("checkout/$firstName/$lastName/${route.text}/$selectedDate/$selectedTime")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                ) {
                    Text(text = "Proceed to Checkout", color = Color.White)
                }
            }
        }
    )
}

@Composable
@Preview
fun BookingPrev() {
    BookingScreen(navController = rememberNavController(), firstName = "John", lastName = "Doe")
}
