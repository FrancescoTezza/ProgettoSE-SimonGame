package com.example.progettose_simongame.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.progettose_simongame.R

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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        items(games) { game ->

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
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = count.toString(),
                                        modifier = Modifier.width(40.dp)
                                    )

                                    Text(
                                        text = game,
                                        modifier = Modifier.weight(1f),
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
}