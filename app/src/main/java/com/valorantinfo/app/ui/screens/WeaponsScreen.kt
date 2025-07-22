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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valorantinfo.app.R
import com.valorantinfo.app.data.model.Weapon
import com.valorantinfo.app.ui.theme.ValorantGold
import com.valorantinfo.app.ui.theme.ValorantRed
import com.valorantinfo.app.viewmodel.UiState
import com.valorantinfo.app.viewmodel.WeaponsViewModel

@Composable
fun WeaponsScreen(
    onBackClick: () -> Unit,
    viewModel: WeaponsViewModel = viewModel()
) {
    val weaponsState by viewModel.weaponsState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.weapons)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
        
        when (val currentState = weaponsState) {
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
                val weapons = currentState.data
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(weapons) { weapon ->
                        WeaponCard(weapon = weapon)
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
fun WeaponCard(weapon: Weapon) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(weapon.displayIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = weapon.displayName,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.1f)),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = weapon.displayName,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = weapon.category,
                    style = MaterialTheme.typography.body2,
                    color = ValorantRed,
                    modifier = Modifier.padding(top = 2.dp)
                )
                
                weapon.shopData?.let { shopData ->
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cost: ",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "${shopData.cost}",
                            style = MaterialTheme.typography.body2,
                            color = ValorantGold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                weapon.weaponStats?.let { stats ->
                    Row(
                        modifier = Modifier.padding(top = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Fire Rate: ",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "${stats.fireRate}",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Magazine: ",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "${stats.magazineSize}",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}
