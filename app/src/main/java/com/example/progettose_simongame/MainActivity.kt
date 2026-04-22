package com.example.progettose_simongame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.progettose_simongame.ui.theme.ProgettoSESimonGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProgettoSESimonGameTheme {
                SimonScreen()
            }
        }
    }
}

@Composable
fun SimonScreen() {

    var sequence by rememberSaveable{ mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Sequenza : $sequence",
            fontSize = 24.sp
        )

        ColorGrid(
            onColorClick = {
                letter -> sequence =
                if(sequence.isEmpty()) letter
                else "$sequence, $letter"
            }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { sequence = "" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancella")
            }

            Button(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("Fine partita")
            }
        }
    }
}


@Composable
fun ColorGrid(onColorClick: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ColorRow("R", "G", Color.Red, Color.Green, onColorClick)
        ColorRow("B", "M", Color.Blue, Color.Magenta, onColorClick)
        ColorRow("Y", "C", Color.Yellow, Color.Cyan, onColorClick)
    }
}

@Composable
fun ColorRow(
    leftText: String,
    rightText: String,
    leftColor: Color,
    rightColor: Color,
    onColorClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .background(leftColor)
                .clickable { onColorClick(leftText) }
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .background(rightColor)
                .clickable { onColorClick(rightText) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SimonScreenPreview() {
    ProgettoSESimonGameTheme {
        SimonScreen()
    }
}