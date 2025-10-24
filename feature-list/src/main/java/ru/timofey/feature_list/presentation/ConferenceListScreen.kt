package ru.timofey.feature_list.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.timofey.core_ui.components.AppToolbar
import ru.timofey.feature_list.presentation.components.ConferenceListContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ConferenceListScreen(
    onConferenceClick: (Long) -> Unit,
    viewModel: ConferenceListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()


    Scaffold(
        topBar = { AppToolbar(title = "Конференции", onBackClick = { }, onHeadphonesClick = {}) }
    ) { padding ->
        ConferenceListContent(
            state = uiState,
            onConferenceClick = onConferenceClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}
