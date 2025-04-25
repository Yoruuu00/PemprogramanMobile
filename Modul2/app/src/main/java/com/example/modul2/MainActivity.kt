package com.example.modul2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                TipCalculatorScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculatorScreen() {
    var costInput by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(0.18) }
    var roundUp by remember { mutableStateOf(false) }
    var tipResult by remember { mutableStateOf("") }

    val cost = costInput.toDoubleOrNull() ?: 0.0
    var tip = cost * tipPercent
    if (roundUp) tip = ceil(tip)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Tip Time",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        )

        TextField(
            value = costInput,
            onValueChange = { costInput = it },
            placeholder = { Text("Cost of Service?") },            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        )

        Text(
            text = "How was the service?",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Column {
            TipOption("Amazing (20%)", 0.20, tipPercent) { tipPercent = it }
            TipOption("Good (18%)", 0.18, tipPercent) { tipPercent = it }
            TipOption("Okay (15%)", 0.15, tipPercent) { tipPercent = it }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Round up tip?")
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }

        Button(
            onClick = {
                tipResult = "Tip Amount: $${"%.2f".format(tip)}"
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text("CALCULATE", color = Color.White)
        }

        Text(
            text = if (tipResult.isEmpty()) "Tip Amount" else tipResult,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun TipOption(text: String, value: Double, selected: Double, onSelect: (Double) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected == value,
            onClick = { onSelect(value) }
        )
        Text(text)
    }
}
