package com.valorantinfo.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.valorantinfo.app.ui.screens.HomeScreen
import com.valorantinfo.app.ui.screens.AgentsScreen
import com.valorantinfo.app.ui.screens.MapsScreen
import com.valorantinfo.app.ui.screens.WeaponsScreen
import com.valorantinfo.app.ui.screens.AgentDetailScreen
import com.valorantinfo.app.ui.screens.BundlesScreen
import com.valorantinfo.app.ui.screens.GamemodesScreen
import com.valorantinfo.app.ui.screens.CurrenciesScreen
import com.valorantinfo.app.ui.screens.EventsScreen
import com.valorantinfo.app.ui.screens.ContentTiersScreen

object Destinations {
    const val HOME = "home"
    const val AGENTS = "agents"
    const val MAPS = "maps"
    const val WEAPONS = "weapons"
    const val AGENT_DETAIL = "agent_detail"
    const val BUDDIES = "buddies"
    const val PLAYER_CARDS = "player_cards"
    const val SPRAYS = "sprays"
    const val BUNDLES = "bundles"
    const val COMPETITIVE_TIERS = "competitive_tiers"
    const val GAMEMODES = "gamemodes"
    const val CURRENCIES = "currencies"
    const val EVENTS = "events"
    const val CONTENT_TIERS = "content_tiers"
}

@Composable
fun ValorantNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {
        composable(Destinations.HOME) {
            HomeScreen(
                onNavigateToAgents = { navController.navigate(Destinations.AGENTS) },
                onNavigateToMaps = { navController.navigate(Destinations.MAPS) },
                onNavigateToWeapons = { navController.navigate(Destinations.WEAPONS) },
                onNavigateToBundles = { navController.navigate(Destinations.BUNDLES) },
                onNavigateToGamemodes = { navController.navigate(Destinations.GAMEMODES) },
                onNavigateToCurrencies = { navController.navigate(Destinations.CURRENCIES) },
                onNavigateToEvents = { navController.navigate(Destinations.EVENTS) },
                onNavigateToContentTiers = { navController.navigate(Destinations.CONTENT_TIERS) }
            )
        }
        
        composable(Destinations.AGENTS) {
            AgentsScreen(
                onAgentClick = { agentId ->
                    navController.navigate("${Destinations.AGENT_DETAIL}/$agentId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(Destinations.MAPS) {
            MapsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(Destinations.WEAPONS) {
            WeaponsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(
            route = "${Destinations.AGENT_DETAIL}/{agentId}",
            arguments = listOf(navArgument("agentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val agentId = backStackEntry.arguments?.getString("agentId") ?: ""
            AgentDetailScreen(
                agentId = agentId,
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(Destinations.BUDDIES) {
            // BuddiesScreen(
            //     onBackPressed = { navController.popBackStack() }
            // )
        }
        
        composable(Destinations.PLAYER_CARDS) {
            // PlayerCardsScreen(
            //     onBackPressed = { navController.popBackStack() }
            // )
        }
        
        composable(Destinations.SPRAYS) {
            // SpraysScreen(
            //     onBackPressed = { navController.popBackStack() }
            // )
        }
        
        composable(Destinations.BUNDLES) {
            BundlesScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        
        composable(Destinations.COMPETITIVE_TIERS) {
            // CompetitiveTiersScreen(
            //     onBackPressed = { navController.popBackStack() }
            // )
        }
        
        composable(Destinations.GAMEMODES) {
            GamemodesScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        
        composable(Destinations.CURRENCIES) {
            CurrenciesScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        
        composable(Destinations.EVENTS) {
            EventsScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        
        composable(Destinations.CONTENT_TIERS) {
            ContentTiersScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
