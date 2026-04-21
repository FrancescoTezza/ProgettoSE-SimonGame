package com.example.progettose_simongame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Sequenza:",
            fontSize = 24.sp
        )

        ColorGrid()

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {},
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
fun ColorGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ColorRow("Rosso", "Verde")
        ColorRow("Blu", "Magenta")
        ColorRow("Giallo", "Ciano")
    }
}

@Composable
fun ColorRow(left: String, right: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text(left)
        }

        Button(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text(right)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimonScreenPreview() {
    ProgettoSESimonGameTheme {
        SimonScreen()
    }
}