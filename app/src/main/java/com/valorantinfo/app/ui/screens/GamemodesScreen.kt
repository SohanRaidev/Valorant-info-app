package com.valorantinfo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Games
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.valorantinfo.app.data.model.Gamemode
import com.valorantinfo.app.ui.UiState
import com.valorantinfo.app.ui.theme.ValorantRed
import com.valorantinfo.app.viewmodel.GamemodesViewModel

@Composable
fun GamemodesScreen(
    onBackPressed: () -> Unit,
    viewModel: GamemodesViewModel = viewModel()
) {
    val gamemodesState by viewModel.gamemodesState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("Game Modes") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            backgroundColor = ValorantRed,
            contentColor = Color.White
        )

        when (val state = gamemodesState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = ValorantRed)
                }
            }
            is UiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.retry() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = ValorantRed)
                    ) {
                        Text("Retry", color = Color.White)
                    }
                }
            }
            is UiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.data) { gamemode ->
                        GamemodeCard(gamemode = gamemode)
                    }
                }
            }
        }
    }
}

@Composable
private fun GamemodeCard(gamemode: Gamemode) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display icon or fallback to material icon
            if (!gamemode.displayIcon.isNullOrBlank() || !gamemode.listViewIconTall.isNullOrBlank()) {
                AsyncImage(
                    model = gamemode.displayIcon?.takeIf { it.isNotBlank() } 
                        ?: gamemode.listViewIconTall?.takeIf { it.isNotBlank() },
                    contentDescription = gamemode.displayName,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Games,
                    contentDescription = gamemode.displayName,
                    tint = ValorantRed,
                    modifier = Modifier.size(64.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = gamemode.displayName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                
                gamemode.description?.let { description ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                        maxLines = 2
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Game mode details
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        if (gamemode.roundsPerHalf > 0) {
                            Text(
                                text = "Rounds: ${gamemode.roundsPerHalf}/half",
                                fontSize = 12.sp,
                                color = ValorantRed
                            )
                        }
                        
                        gamemode.duration?.let { duration ->
                            Text(
                                text = "Duration: $duration",
                                fontSize = 12.sp,
                                color = ValorantRed
                            )
                        }
                    }
                    
                    Column {
                        if (gamemode.allowsMatchTimeouts) {
                            Text(
                                text = "Timeouts: Yes",
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                            )
                        }
                        
                        if (gamemode.orbCount > 0) {
                            Text(
                                text = "Orbs: ${gamemode.orbCount}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}
