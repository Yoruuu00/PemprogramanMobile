package com.example.modul5.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.modul5.presentation.viewmodel.DinoViewModel

@Composable
fun DinoDetailScreen(viewModel: DinoViewModel, navController: NavController) {
    val selectedDino = viewModel.selectedDino.collectAsState().value

    if (selectedDino == null) {
        Text("Data tidak tersedia")
        return
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        AsyncImage(
            model = selectedDino.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            selectedDino.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            selectedDino.description,
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("dino_list") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Kembali")
        }
    }
}
