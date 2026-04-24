package com.example.progettose_simongame

import android.os.Bundle
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.material3.Card
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.progettose_simongame.ui.theme.ProgettoSESimonGameTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow

// Punto di ingresso dell'app e gestione navigazione tra schermate
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProgettoSESimonGameTheme {
                val navController = rememberNavController()

                val games = rememberSaveable {
                    mutableStateListOf<String>()
                }

                NavHost(
                    navController = navController,
                    startDestination = "game"
                )  {
                    composable("game") {
                        SimonScreen(navController, games)
                    }

                    composable("history") {
                        HistoryScreen(games = games)
                    }
                }
            }
        }
    }
}

// Schermata 1 del gioco
@Composable
fun SimonScreen(
    navController: NavHostController,
    games: SnapshotStateList<String>
) {

    var sequence by rememberSaveable{ mutableStateOf("") }

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {

        LandscapeLayout(
            sequence = sequence,

            onColorClick = { letter ->
                sequence =
                    if (sequence.isEmpty()) letter
                    else "$sequence, $letter"
            },

            onClear = {
                sequence = ""
            },

            onEndGame = {
                games.add(sequence)
                sequence = ""
                navController.navigate("history")
            }
        )

    } else {

        PortraitLayout(
            sequence = sequence,

            onColorClick = { letter ->
                sequence =
                    if (sequence.isEmpty()) letter
                    else "$sequence, $letter"
            },

            onClear = {
                sequence = ""
            },

            onEndGame = {
                games.add(sequence)
                sequence = ""
                navController.navigate("history")
            }
        )
    }
}

// Layout verticale usato in orientamento portrait
@Composable
fun PortraitLayout(
    sequence: String,
    onColorClick: (String) -> Unit,
    onClear: () -> Unit,
    onEndGame: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

    ColorGrid(onColorClick)

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "${stringResource(R.string.sequence)}: $sequence",
            fontSize = 22.sp,
            modifier = Modifier.padding(16.dp)
        )
    }

    ButtonsRow(onClear, onEndGame)
    }
}

// Layout orizzontale usato in orientamento landscape
@Composable
fun LandscapeLayout(
    sequence: String,
    onColorClick: (String) -> Unit,
    onClear: () -> Unit,
    onEndGame: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(modifier = Modifier.weight(1f)) {
            ColorGrid(onColorClick)
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${stringResource(R.string.sequence)}: $sequence",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            ButtonsRow(onClear, onEndGame)
        }
    }
}

// Pulsanti di controllo: "Cancella" e "Fine partita"
@Composable
fun ButtonsRow(
    onClear: () -> Unit,
    onEndGame: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Button(
            onClick = onClear,
            modifier = Modifier
                .weight(1f)
                .height(54.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(stringResource(R.string.clear))
        }

        Button(
            onClick = onEndGame,
            modifier = Modifier
                .weight(1f)
                .height(54.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(stringResource(R.string.end_game))
        }
    }
}

// Griglia 3x2 dei pulsanti colorati
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

//classe utilizzata in Color Grid per creare la matrice di colori
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
                .clip(RoundedCornerShape(16.dp))
                .background(leftColor)
                .clickable { onColorClick(leftText) }
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(rightColor)
                .clickable { onColorClick(rightText) }
        )
    }
}

//Schermata 2 del gioco
@Composable
fun HistoryScreen(
    games: SnapshotStateList<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = stringResource(R.string.history_title),
            fontSize = 26.sp
        )

        if (games.isEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.no_games),
                    modifier = Modifier.padding(16.dp)
                )
            }

        } else {

            games.forEachIndexed { index, game ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    games.forEach { game ->

                        val count =
                            if (game.isBlank()) 0
                            else game.split(",").size

                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                Text(
                                    text = count.toString(),
                                    fontSize = 18.sp
                                )

                                Text(
                                    text = game,
                                    fontSize = 18.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis //necessaria per il troncamento
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun SimonScreenPreview() {

    ProgettoSESimonGameTheme {
        SimonScreen(
            navController = rememberNavController(),
            games = remember { mutableStateListOf<String>() }
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 900,
    heightDp = 400
)
@Composable
fun SimonScreenLandscapePreview() {

    ProgettoSESimonGameTheme {
        SimonScreen(
            navController = rememberNavController(),
            games = remember { mutableStateListOf<String>() }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {

    val fakeGames = remember {
        mutableStateListOf(
            "R, G, B",
            "Y, C",
            "",
            "R, R, G, Y, M"
        )
    }

    ProgettoSESimonGameTheme {
        HistoryScreen(
            games = fakeGames
        )
    }
}
*/
