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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valorantinfo.app.R
import com.valorantinfo.app.data.model.GameMap
import com.valorantinfo.app.ui.theme.ValorantRed
import com.valorantinfo.app.viewmodel.MapsViewModel
import com.valorantinfo.app.viewmodel.UiState

@Composable
fun MapsScreen(
    onBackClick: () -> Unit,
    viewModel: MapsViewModel = viewModel()
) {
    val mapsState by viewModel.mapsState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.maps)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
        
        when (val currentState = mapsState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = ValorantRed)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.loading),
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
            
            is UiState.Success -> {
                val maps = currentState.data
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(maps) { map ->
                        MapCard(map = map)
                    }
                }
            }
            
            is UiState.Error -> {
                ErrorScreen(
                    message = stringResource(R.string.error_network),
                    onRetry = { viewModel.retryFetch() }
                )
            }
        }
    }
}

@Composable
fun MapCard(map: GameMap) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(map.splash ?: map.displayIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = map.displayName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(Color.Gray.copy(alpha = 0.1f)),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = map.displayName,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
                
                map.coordinates?.let { coordinates ->
                    Text(
                        text = coordinates,
                        style = MaterialTheme.typography.body2,
                        color = ValorantRed,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                
                map.tacticalDescription?.let { description ->
                    Text(
                        text = description,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
