package com.example.uts_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainScreen(navController) }
                composable("detail") { DetailScreen(navController) }
            }
        }
    }
}

data class Student(
    val name: String,
    val major: String,
    val hobby: String,
    val ambition: String,
    val motto: String,
    val Deskripsi: String,
    val photoResId: Int,
)

val student = Student(
    name = "Muhammad Rizki Saputra",
    major = "Teknologi Informasi",
    hobby = "Tidur, main game, menonton dan membaca novel",
    ambition = "Menjadi Raja Iblis",
    motto = "Sukses menjadi perkap IT Matsuri",
    Deskripsi = """
        Muhammad Rizki Saputra, yang biasa dipanggil Rizki, lahir pada tahun 2005. Ia adalah mahasiswa Universitas Lambung Mangkurat, Program Studi Teknologi Informasi, Angkatan 2023.

        Rizki memilih jurusan Teknologi Informasi karena tertarik dengan bidang Cyber Security dan UI/UX. Ia juga memiliki keahlian dalam UI/UX dan pengembangan game.

        Rizki memiliki hobi membaca novel, bermain game, dan mendengarkan musik dengan genre favorit Pop, R&B/Soul, dan Indie.

        Dalam dunia organisasi, Rizki aktif di Himpunan Mahasiswa Teknologi Informasi Divisi 3 (Pemberdayaan Sumber Daya Mahasiswa). Sebelumnya, ia juga tergabung di Divisi 4 (Minat & Bakat).

        Selama mengikuti organisasi, Rizki merasakan banyak perkembangan diri (self-improvement), terutama dalam kemampuan mengelola kegiatan dan meningkatkan keterampilan sosial yang sebelumnya masih terbatas.
    """.trimIndent(),
    photoResId = R.drawable.rizki
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Profil Mahasiswa", style = MaterialTheme.typography.titleLarge)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("detail") },
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = student.photoResId),
                        contentDescription = "Foto Mahasiswa",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = student.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Jurusan: ${student.major}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Text(
                            text = "Cita-cita: ${student.ambition}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Mahasiswa") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            )
        }
    ) { padding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.CenterHorizontally),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Image(
                    painter = painterResource(id = student.photoResId),
                    contentDescription = "Foto Mahasiswa",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Text("Nama: ${student.name}", style = MaterialTheme.typography.bodyLarge)
            Text("Jurusan: ${student.major}", style = MaterialTheme.typography.bodyLarge)
            Text("Hobi: ${student.hobby}", style = MaterialTheme.typography.bodyLarge)
            Text("Cita-cita: ${student.ambition}", style = MaterialTheme.typography.bodyLarge)
            Text("Motto: ${student.motto}", style = MaterialTheme.typography.bodyLarge)

            Text("Deskripsi:", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = student.Deskripsi,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
