package com.example.modul3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Tambahkan tipe dinosaurus
data class DinoCard(
    val name: String,
    val description: String,
    val imageRes: Int,
    val type: String // "Herbivora" atau "Karnivora"
)

val dinoCards = listOf(
    DinoCard(
        "Tyrannosaurus Rex",
        """
        Tyrannosaurus Rex adalah predator terbesar di zamannya, hidup sekitar 68 hingga 66 juta tahun yang lalu di akhir periode Kapur. Dengan panjang mencapai 12 meter dan tinggi hampir 4 meter, T-Rex memiliki rahang sangat kuat dan gigi setajam pisau untuk merobek daging mangsanya.

        Meskipun tangan depannya kecil, tubuhnya yang besar dan kekuatan gigitan luar biasa menjadikannya salah satu dinosaurus paling menakutkan. Fosilnya ditemukan di Amerika Utara dan menjadi ikon dalam dunia paleontologi.
        """.trimIndent(),
        R.drawable.tyrannosaurusrex,
        "Karnivora"
    ),
    DinoCard(
        "Triceratops",
        """
        Triceratops adalah dinosaurus herbivora besar dengan tiga tanduk dan pelindung tengkorak besar, hidup berdampingan dengan T-Rex di akhir Kapur sekitar 68 juta tahun yang lalu. Ia mencapai panjang sekitar 9 meter dan berat hingga 12 ton.

        Tanduk panjangnya diyakini digunakan untuk pertahanan diri dan pertarungan sesama jantan saat musim kawin. Struktur tengkoraknya juga mungkin berfungsi untuk pengaturan suhu tubuh atau sebagai alat komunikasi visual.
        """.trimIndent(),
        R.drawable.triceratops,
        "Herbivora"
    ),
    DinoCard(
        "Velociraptor",
        """
        Velociraptor adalah dinosaurus kecil dan karnivora yang terkenal karena kecepatannya dan diduga memiliki bulu. Ia hidup sekitar 75 hingga 71 juta tahun yang lalu di wilayah yang kini merupakan Mongolia.

        Meski hanya seukuran kalkun, Velociraptor sangat gesit dan cerdas, serta berburu dalam kelompok. Cakar melengkung pada kaki belakangnya digunakan untuk mencengkram dan melumpuhkan mangsa.
        """.trimIndent(),
        R.drawable.velociraptor,
        "Karnivora"
    ),
    DinoCard(
        "Stegosaurus",
        """
        Stegosaurus adalah dinosaurus herbivora dari periode Jurassic akhir, terkenal dengan piring tulang besar di punggung dan ekor berduri yang disebut thagomizer. Ia hidup sekitar 155 juta tahun lalu dan memiliki otak relatif kecil.

        Piring punggungnya kemungkinan digunakan untuk menakuti pemangsa, menarik pasangan, atau mengatur suhu tubuh. Ekor berdurinya menjadi senjata efektif melawan predator seperti Allosaurus.
        """.trimIndent(),
        R.drawable.stegosaurus,
        "Herbivora"
    ),
    DinoCard(
        "Brachiosaurus",
        """
        Brachiosaurus adalah salah satu sauropoda terbesar yang hidup sekitar 154 hingga 150 juta tahun lalu pada periode Jurassic. Ciri khasnya adalah leher panjang yang memungkinkan ia meraih daun dari pohon tinggi.

        Berbeda dari sauropoda lain, kaki depan Brachiosaurus lebih panjang daripada kaki belakang, membuat posturnya menjulang tinggi. Ia diperkirakan memiliki berat hingga 40 ton dan panjang lebih dari 25 meter.
        """.trimIndent(),
        R.drawable.brachiosaurus,
        "Herbivora"
    ),
    DinoCard(
        "Spinosaurus",
        """
        Spinosaurus adalah dinosaurus karnivora terbesar yang hidup sekitar 112–93 juta tahun lalu di Afrika Utara. Ciri utamanya adalah layar punggung tinggi seperti layar kapal, kemungkinan digunakan untuk menampilkan diri atau mengatur suhu.

        Ia diyakini sebagai semi-akuatik, berburu ikan dan hewan air lainnya di sungai purba. Dengan panjang mencapai 15 meter, Spinosaurus lebih besar dari T-Rex dan sangat adaptif terhadap lingkungan air.
        """.trimIndent(),
        R.drawable.spinosaurus,
        "Karnivora"
    ),
    DinoCard(
        "Ankylosaurus",
        """
        Ankylosaurus adalah dinosaurus herbivora dari akhir periode Kapur, terkenal karena tubuhnya yang dilapisi zira dan ekor besar berbentuk gada. Panjangnya mencapai 6 hingga 8 meter dan berat hingga 8 ton.

        Bentuk tubuhnya seperti tank membuatnya hampir kebal terhadap serangan predator. Ekor berotot dan keras dapat digunakan untuk menyerang balik dan melukai lawan secara fatal.
        """.trimIndent(),
        R.drawable.ankylosaurus,
        "Herbivora"
    ),
    DinoCard(
        "Allosaurus",
        """
        Allosaurus adalah predator utama dari periode Jurassic, hidup sekitar 155 hingga 145 juta tahun lalu. Ia memiliki tengkorak besar, gigi tajam, dan tubuh ramping yang memungkinkannya berburu dengan kecepatan tinggi.

        Allosaurus sering disebut 'singa Jurassic' karena perannya sebagai pemburu puncak. Ia mungkin berburu dalam kelompok dan mangsanya termasuk sauropoda besar seperti Diplodocus dan Camarasaurus.
        """.trimIndent(),
        R.drawable.allosaurus,
        "Karnivora"
    ),
    DinoCard(
        "Diplodocus",
        """
        Diplodocus adalah sauropoda raksasa dari periode Jurassic, dikenal karena leher dan ekor super panjang. Panjang tubuhnya bisa mencapai 27 meter, menjadikannya salah satu dinosaurus terpanjang yang pernah hidup.

        Ia hidup di Amerika Utara sekitar 154 juta tahun lalu dan memakan tumbuhan rendah di hutan purba. Ekor panjangnya kemungkinan digunakan untuk pertahanan atau komunikasi sonik seperti cambuk.
        """.trimIndent(),
        R.drawable.diplodocus,
        "Herbivora"
    ),
    DinoCard(
        "Parasaurolophus",
        """
        Parasaurolophus adalah dinosaurus herbivora dari akhir Kapur yang dikenal karena jambul panjang berongga di kepalanya. Jambul tersebut kemungkinan digunakan untuk menghasilkan suara, menarik pasangan, atau membantu mengatur suhu tubuh.

        Ia berjalan dengan dua atau empat kaki dan hidup dalam kawanan. Panjang tubuhnya sekitar 10 meter dan dikenal sebagai bagian dari kelompok hadrosaur atau dinosaurus bebek.
        """.trimIndent(),
        R.drawable.parasaurolophus,
        "Herbivora"
    )
)



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                Scaffold { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dino_list",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("dino_list") {
                            DinoListScreen(navController)
                        }
                        composable("detail/{name}/{desc}/{imgRes}") { backStack ->
                            val name = backStack.arguments?.getString("name")
                            val desc = backStack.arguments?.getString("desc")
                            val imgRes = backStack.arguments?.getString("imgRes")?.toIntOrNull()

                            if (name != null && desc != null && imgRes != null) {
                                DinoDetailScreen(name, desc, imgRes, navController)
                            } else {
                                Text("Data tidak lengkap")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DinoListScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Semua") }
    val types = listOf("Semua", "Herbivora", "Karnivora")

    val filteredList = dinoCards.filter {
        it.name.contains(searchQuery, ignoreCase = true) &&
                (selectedFilter == "Semua" || it.type == selectedFilter)
    }

    Column(
        modifier = Modifier
            .background(Color(0xFFF4F1DE))
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF81B29A))
                .padding(16.dp)
        ) {
            Text(
                text = "Jenis-Jenis Dinosaurus",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Cari dinosaurus...") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Filter berdasarkan tipe:", fontSize = 14.sp)
            DropdownMenuBox(types, selectedFilter) { selectedFilter = it }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredList) { dino ->
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7F0)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Image(
                            painter = painterResource(id = dino.imageRes),
                            contentDescription = dino.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            dino.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3D405B)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Button(
                            onClick = {
                                navController.navigate("detail/${dino.name}/${dino.description}/${dino.imageRes}")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81B29A)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Info Detail", color = Color.White, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuBox(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81B29A))
        ) {
            Text(selectedOption, color = Color.White)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { label ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        onOptionSelected(label)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DinoDetailScreen(name: String, desc: String, imgRes: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = imgRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(desc, fontSize = 14.sp, textAlign = TextAlign.Justify)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("dino_list") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Kembali")
        }
    }
}