package com.valorantinfo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.valorantinfo.app.data.model.ContentTier
import com.valorantinfo.app.ui.UiState
import com.valorantinfo.app.ui.theme.ValorantRed
import com.valorantinfo.app.viewmodel.ContentTiersViewModel

@Composable
fun ContentTiersScreen(
    onBackPressed: () -> Unit,
    viewModel: ContentTiersViewModel = viewModel()
) {
    val contentTiersState by viewModel.contentTiersState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("Content Tiers") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            backgroundColor = ValorantRed,
            contentColor = Color.White
        )

        when (val state = contentTiersState) {
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
                    items(state.data) { contentTier ->
                        ContentTierCard(contentTier = contentTier)
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentTierCard(contentTier: ContentTier) {
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
            AsyncImage(
                model = contentTier.displayIcon ?: contentTier.highlightColor,
                contentDescription = contentTier.devName,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = contentTier.devName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                
                Text(
                    text = "Rank: ${contentTier.rank}",
                    fontSize = 14.sp,
                    color = ValorantRed
                )
                
                contentTier.juiceValue?.let { juiceValue ->
                    Text(
                        text = "Juice Value: $juiceValue",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
                
                contentTier.juiceCost?.let { juiceCost ->
                    Text(
                        text = "Juice Cost: $juiceCost",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
