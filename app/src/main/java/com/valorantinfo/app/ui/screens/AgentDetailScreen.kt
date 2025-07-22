package com.valorantinfo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valorantinfo.app.data.model.Ability
import com.valorantinfo.app.data.model.Agent
import com.valorantinfo.app.ui.theme.ValorantRed
import com.valorantinfo.app.viewmodel.AgentsViewModel
import com.valorantinfo.app.viewmodel.UiState

@Composable
fun AgentDetailScreen(
    agentId: String,
    onBackClick: () -> Unit,
    viewModel: AgentsViewModel = viewModel()
) {
    val agentsState by viewModel.agentsState.collectAsState()
    
    var selectedAgent by remember { mutableStateOf<Agent?>(null) }
    
    LaunchedEffect(agentsState) {
        val currentState = agentsState
        if (currentState is UiState.Success) {
            val agents = currentState.data
            selectedAgent = agents.find { it.uuid == agentId }
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = { Text(selectedAgent?.displayName ?: "Agent Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
        
        selectedAgent?.let { agent ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    AgentHeader(agent = agent)
                }
                
                item {
                    Text(
                        text = "Abilities",
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                items(agent.abilities) { ability ->
                    AbilityCard(ability = ability)
                }
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = ValorantRed)
            }
        }
    }
}

@Composable
fun AgentHeader(agent: Agent) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agent.fullPortrait ?: agent.displayIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = agent.displayName,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Gray.copy(alpha = 0.1f)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = agent.displayName,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold
            )
            
            agent.role?.let { role ->
                Text(
                    text = role.displayName,
                    style = MaterialTheme.typography.h3,
                    color = ValorantRed,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            Text(
                text = agent.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun AbilityCard(ability: Ability) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(ability.displayIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = ability.displayName,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.1f)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = ability.slot.uppercase(),
                        style = MaterialTheme.typography.caption,
                        color = ValorantRed,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = ability.displayName,
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Text(
                    text = ability.description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
