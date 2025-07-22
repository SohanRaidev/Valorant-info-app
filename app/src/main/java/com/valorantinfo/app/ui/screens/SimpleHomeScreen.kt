package com.valorantinfo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay
import com.valorantinfo.app.R
import com.valorantinfo.app.ui.theme.ValorantRed

val GoldmanFont = FontFamily(
    Font(R.font.goldman_regular, FontWeight.Normal),
    Font(R.font.goldman_bold, FontWeight.Bold)
)

@Composable
fun HomeScreen(
    onNavigateToAgents: () -> Unit = {},
    onNavigateToMaps: () -> Unit = {},
    onNavigateToWeapons: () -> Unit = {},
    onNavigateToBuddies: () -> Unit = {},
    onNavigateToPlayerCards: () -> Unit = {},
    onNavigateToSprays: () -> Unit = {},
    onNavigateToBundles: () -> Unit = {},
    onNavigateToCompetitiveTiers: () -> Unit = {},
    onNavigateToGamemodes: () -> Unit = {},
    onNavigateToCurrencies: () -> Unit = {},
    onNavigateToEvents: () -> Unit = {},
    onNavigateToContentTiers: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Valorant Info",
                style = MaterialTheme.typography.h1.copy(fontFamily = GoldmanFont),
                color = ValorantRed,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Your ultimate Valorant reference guide",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.Person,
                title = "Agents",
                description = "Browse all agents and their abilities",
                onClick = onNavigateToAgents,
                index = 0
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.Place,
                title = "Maps",
                description = "Discover all maps and their callouts",
                onClick = onNavigateToMaps,
                index = 1
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.Star,
                title = "Weapons",
                description = "Browse weapons, stats, and skins",
                onClick = onNavigateToWeapons,
                index = 2
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.ShoppingBag,
                title = "Bundles",
                description = "Browse all cosmetic bundles",
                onClick = onNavigateToBundles,
                index = 3
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.Games,
                title = "Game Modes",
                description = "Learn about all game modes",
                onClick = onNavigateToGamemodes,
                index = 4
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.MonetizationOn,
                title = "Currencies",
                description = "Check all in-game currencies",
                onClick = onNavigateToCurrencies,
                index = 5
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.Event,
                title = "Events",
                description = "View past and current events",
                onClick = onNavigateToEvents,
                index = 6
            )
        }
        
        item {
            AnimatedCategoryCard(
                icon = Icons.Default.Diamond,
                title = "Content Tiers",
                description = "Explore all content tiers and rarities",
                onClick = onNavigateToContentTiers,
                index = 7
            )
        }
    }
}

@Composable
fun CategoryCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 6.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        elevation = elevation,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = ValorantRed,
                modifier = Modifier.size(48.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun AnimatedCategoryCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit,
    index: Int
) {
    var isVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(index * 100L) // Stagger animation by 100ms per item
        isVisible = true
    }
    
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 300,
                delayMillis = index * 50
            )
        )
    ) {
        CategoryCard(
            icon = icon,
            title = title,
            description = description,
            onClick = onClick
        )
    }
}
