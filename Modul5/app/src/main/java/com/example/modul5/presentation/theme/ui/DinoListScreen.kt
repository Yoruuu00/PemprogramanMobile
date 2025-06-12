package com.example.modul5.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.modul5.data.Resource
import com.example.modul5.presentation.viewmodel.DinoViewModel
import com.example.modul5.presentation.viewmodel.SettingsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DinoListScreen(
    navController: NavController,
    viewModel: DinoViewModel,
    settingsViewModel: SettingsViewModel,
    isDarkMode: Boolean
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedDiet by remember { mutableStateOf("All") }
    var expanded by remember { mutableStateOf(false) }

    val dinoListState by viewModel.dinoListState.collectAsState()
    val dietOptions = listOf("All", "Herbivora", "Karnivora", "Omnivora")
    val systemUiController = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colorScheme.primary
    val colorScheme = MaterialTheme.colorScheme

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor, darkIcons = !isDarkMode)
    }

    LaunchedEffect(key1 = dinoListState) {
        if (dinoListState is Resource.Error && dinoListState.data?.isNotEmpty() == true) {
            Toast.makeText(context, dinoListState.message, Toast.LENGTH_LONG).show()
        }
    }

    val filteredList = dinoListState.data?.filter { dino ->
        val matchesSearch = dino.name.contains(searchQuery, ignoreCase = true)
        val apiDiet = when (selectedDiet) {
            "Herbivora" -> "herbivore"
            "Karnivora" -> "carnivore"
            "Omnivora" -> "omnivore"
            else -> "All"
        }
        val matchesDiet = apiDiet == "All" || dino.diet.equals(apiDiet, ignoreCase = true)
        matchesSearch && matchesDiet
    } ?: emptyList()

    Column(modifier = Modifier.background(colorScheme.background).fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Jenis-Jenis Dinosaurus",
                fontSize = 24.sp,
                color = colorScheme.onPrimary,
            )
            IconButton(onClick = { settingsViewModel.toggleDarkMode() }) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = "Toggle Dark Mode",
                    tint = colorScheme.onPrimary
                )
            }
        }


        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Cari dinosaurus...") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorScheme.primary,
                unfocusedBorderColor = colorScheme.outline,
                cursorColor = colorScheme.primary
            )
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedButton(onClick = { expanded = true }) {
                Text("Filter: $selectedDiet")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                dietOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedDiet = option
                            expanded = false
                        }
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredList) { dino ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column {
                            AsyncImage(
                                model = dino.imageUrl,
                                contentDescription = dino.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            )
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    dino.name,
                                    fontSize = 16.sp,
                                    color = colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = {
                                        viewModel.selectDino(dino)
                                        navController.navigate("detail")
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
                                ) {
                                    Text("Info Detail", color = colorScheme.onPrimary, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }

            if (dinoListState is Resource.Loading && filteredList.isEmpty()) {
                CircularProgressIndicator()
            }

            if (dinoListState is Resource.Error && filteredList.isEmpty()) {
                Text(
                    text = dinoListState.message ?: "Gagal memuat data",
                    color = colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}