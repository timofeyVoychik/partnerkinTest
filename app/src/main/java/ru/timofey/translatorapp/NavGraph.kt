package ru.timofey.partnerkintest

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.timofey.feature_conference.presentation.ConferenceDetailScreen
import ru.timofey.feature_conference.presentation.ConferenceDetailViewModel
import ru.timofey.feature_list.presentation.ConferenceListScreen

sealed class AppDestination(val route: String) {
    data object ConferenceList : AppDestination("conference_list")
    data class ConferenceDetail(val id: Long) : AppDestination("conference_detail/{id}") {
        companion object {
            const val baseRoute = "conference_detail"
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.ConferenceList.route
    ) {

        composable(route = AppDestination.ConferenceList.route) {
            ConferenceListScreen(
                onConferenceClick = { conferenceId ->
                    navController.navigate("${AppDestination.ConferenceDetail.baseRoute}/$conferenceId")
                }
            )
        }

        composable(route = "${AppDestination.ConferenceDetail.baseRoute}/{id}") { backStackEntry ->
            val conferenceId = backStackEntry.arguments?.getString("id")?.toLongOrNull()
            BackHandler {
                navController.popBackStack()
            }
            if (conferenceId != null) {
                val viewModel: ConferenceDetailViewModel = hiltViewModel()

                LaunchedEffect(conferenceId) {
                    viewModel.loadConference(conferenceId)
                }

                val state = viewModel.uiState.collectAsState().value

                ConferenceDetailScreen(
                    state = state,
                    onBackClick = { navController.popBackStack() }
                )
            } else {
                androidx.compose.material3.Text("Ошибка: неверный id конференции")
            }
        }

    }
}
