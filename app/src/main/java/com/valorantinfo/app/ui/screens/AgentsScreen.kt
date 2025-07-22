package com.valorantinfo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valorantinfo.app.R
import com.valorantinfo.app.data.model.Agent
import com.valorantinfo.app.ui.theme.ValorantRed
import com.valorantinfo.app.viewmodel.AgentsViewModel
import com.valorantinfo.app.viewmodel.UiState

@Composable
fun AgentsScreen(
    onAgentClick: (String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: AgentsViewModel = viewModel()
) {
    val agentsState by viewModel.agentsState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.agents)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
        
        when (val currentState = agentsState) {
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
                val agents = currentState.data
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(agents) { agent ->
                        AgentCard(
                            agent = agent,
                            onClick = { onAgentClick(agent.uuid) }
                        )
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
fun AgentCard(
    agent: Agent,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
                    .data(agent.displayIconSmall ?: agent.displayIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = agent.displayName,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.1f)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = agent.displayName,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
                
                agent.role?.let { role ->
                    Text(
                        text = role.displayName,
                        style = MaterialTheme.typography.body2,
                        color = ValorantRed,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                
                Text(
                    text = agent.description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(backgroundColor = ValorantRed)
            ) {
                Text(
                    text = stringResource(R.string.retry),
                    color = Color.White
                )
            }
        }
    }
}
