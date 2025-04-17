package com.example.modul1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.modul1.R
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollApp()
        }
    }
}

@Composable
fun DiceRollApp() {
    var dice1 by remember { mutableStateOf(0) }
    var dice2 by remember { mutableStateOf(0) }
    val context = LocalContext.current

    // Cek hasil dadu dan tampilkan toast jika diperlukan
    LaunchedEffect(dice1, dice2) {
        if (dice1 != 0 && dice2 != 0) {
            if (dice1 == dice2) {
                Toast.makeText(context, "Selamat anda dapat dadu double!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = getDiceImage(dice1)),
                contentDescription = "Dadu 1",
                modifier = Modifier.size(100.dp)
            )
            Image(
                painter = painterResource(id = getDiceImage(dice2)),
                contentDescription = "Dadu 2",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            dice1 = Random.nextInt(1, 7)
            dice2 = Random.nextInt(1, 7)
        }) {
            Text(text = "Roll Dice")
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

fun getDiceImage(value: Int): Int {
    return when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }
}
