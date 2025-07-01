package com.example.uasmobile.presentation.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uasmobile.data.local.AppDatabase
import com.example.uasmobile.data.remote.RetrofitInstance
import com.example.uasmobile.data.repository.MusicRepositoryImpl
import com.example.uasmobile.domain.usecase.GetArtistDetailsUseCase
import com.example.uasmobile.domain.usecase.GetChartsUseCase
import com.example.uasmobile.domain.usecase.SearchAlbumsUseCase
import com.example.uasmobile.domain.usecase.SearchArtistsUseCase
import com.example.uasmobile.presentation.viewModel.*
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val musicRepository = MusicRepositoryImpl(RetrofitInstance.api, database.favoriteArtistDao())
        val searchArtistsUseCase = SearchArtistsUseCase(musicRepository)
        val searchAlbumsUseCase = SearchAlbumsUseCase(musicRepository)
        val getArtistDetailsUseCase = GetArtistDetailsUseCase(musicRepository)
        val getChartsUseCase = GetChartsUseCase(musicRepository)

        val viewModelFactory = ViewModelFactory(
            searchArtistsUseCase,
            searchAlbumsUseCase,
            getArtistDetailsUseCase,
            musicRepository,
            getChartsUseCase
        )

        setContent {
            UASMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navigationItems = listOf(
                        NavigationItem.Home,
                        NavigationItem.Genre,
                        NavigationItem.Favorite
                    )

                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                navigationItems.forEach { screen ->
                                    NavigationBarItem(
                                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                                        label = { Text(screen.title) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            AppNavHost(
                                navController = navController,
                                viewModelFactory = viewModelFactory
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModelFactory: ViewModelFactory
) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
            val trendingViewModel: TrendingViewModel = viewModel(factory = viewModelFactory)
            val trendingState = trendingViewModel.state.collectAsState()
            HomeScreen(
                navController = navController,
                trendingArtists = trendingState.value.artists,
                trendingAlbums = trendingState.value.albums,
                onArtistClick = { artistId -> navController.navigate("detail_screen/$artistId") }
            )
        }
        composable(NavigationItem.Genre.route) {
            val viewModel: GenreViewModel = viewModel(factory = viewModelFactory)
            GenreListScreen(navController = navController, viewModel = viewModel)
        }
        composable(NavigationItem.Favorite.route) {
            val viewModel: FavoriteViewModel = viewModel(factory = viewModelFactory)
            FavoriteScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = "search_result/{query}",
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""

            SearchResultScreen(
                navController = navController,
                searchQuery = query,
                viewModelFactory = viewModelFactory,
                onArtistClick = { artistId -> navController.navigate("detail_screen/$artistId") },
                onAlbumClick = { albumId -> navController.navigate("album_detail/$albumId") }
            )
        }
        composable(
            route = "detail_screen/{artistId}",
            arguments = listOf(navArgument("artistId") { type = NavType.StringType })
        ) {
            val viewModel: ArtistDetailViewModel = viewModel(factory = viewModelFactory)
            ArtistDetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            "genre_detail/{genreId}/{genreName}",
            arguments = listOf(
                navArgument("genreId") { type = NavType.StringType },
                navArgument("genreName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val genreName = backStackEntry.arguments?.getString("genreName") ?: ""
            val decodedGenreName = URLDecoder.decode(genreName, StandardCharsets.UTF_8.toString())
            val viewModel: GenreDetailViewModel = viewModel(factory = viewModelFactory)
            GenreDetailScreen(
                navController = navController,
                genreName = decodedGenreName,
                viewModel = viewModel
            )
        }
        composable(
            "album_detail/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) {
            val viewModel: AlbumDetailViewModel = viewModel(factory = viewModelFactory)
            AlbumDetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}