package com.shaun.mybusgo.ui.theme.screens.checkout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.shaun.mybusgo.navigation.BottomNavigationBar

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    firstName: String,
    lastName: String,
    selectedDate: String,
    selectedTime: String,
    route: String
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }

    // Main Layout with Scaffold to hold Bottom Navigation
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Checkout Your Booking", color = Color.Blue, fontSize = 30.sp)
                    Spacer(modifier = Modifier.height(20.dp))

                    // Display the first name and last name
                    Text(text = "Name: $firstName $lastName", color = Color.LightGray, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Route: $route", color = Color.LightGray, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Date: $selectedDate", color = Color.LightGray, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Time: $selectedTime", color = Color.LightGray, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(40.dp))

                    // Loading Indicator
                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Button(
                            onClick = { showDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                        ) {
                            Text(text = "Submit Booking")
                        }
                    }
                }
            }
        }
    )

    // Confirmation Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirm Booking") },
            text = { Text("Are you sure you want to submit your booking?") },
            confirmButton = {
                Button(
                    onClick = {
                        if (validateFields(route, selectedDate, selectedTime)) {
                            isLoading = true // Show loading state
                            saveBookingToFirestore(firstName, lastName, route, selectedDate, selectedTime) { message ->
                                successMessage = message
                                isLoading = false
                                showDialog = false
                                Toast.makeText(context, successMessage, Toast.LENGTH_LONG).show()
                            }
                        } else {
                            errorMessage = "Please fill all fields before submitting."
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}

// Function to save booking to Firestore
private fun saveBookingToFirestore(firstName: String, lastName: String, route: String, date: String, time: String, callback: (String) -> Unit) {
    // Initialize Firestore
    val db = FirebaseFirestore.getInstance()

    val booking = hashMapOf(
        "firstName" to firstName,
        "lastName" to lastName,
        "route" to route,
        "date" to date,
        "time" to time
    )

    db.collection("bookings")
        .add(booking)
        .addOnSuccessListener {
            callback("You have successfully booked a bus! Remember to arrive on time and safe trip!")
        }
        .addOnFailureListener { e ->
            callback("Error saving booking: ${e.message}")
        }
}

// Function to validate booking fields
fun validateFields(route: String, date: String, time: String): Boolean {
    return route.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()
}

@Composable
@Preview
fun CheckoutPrev() {
    CheckoutScreen(
        navController = rememberNavController(),
        firstName = "John", // Sample first name
        lastName = "Doe",   // Sample last name
        selectedDate = "25/12/2024",
        selectedTime = "14:30",
        route = "Nairobi to Mombasa"
    )
}
