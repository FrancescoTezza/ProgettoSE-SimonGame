package com.example.progettose_simongame.ui.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.example.progettose_simongame.LandscapeLayout
import com.example.progettose_simongame.PortraitLayout

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