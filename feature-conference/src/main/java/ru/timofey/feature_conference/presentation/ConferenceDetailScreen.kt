package ru.timofey.feature_conference.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.timofey.core_ui.components.AppToolbar
import ru.timofey.feature_conference.model.ConferenceDetailUiState
import ru.timofey.feature_conference.presentation.components.ConferenceHeader
import ru.timofey.feature_conference.presentation.components.ConferenceInfoSection
import ru.timofey.feature_conference.presentation.components.ConferenceAboutSection

@Composable
fun ConferenceDetailScreen(
    state: ConferenceDetailUiState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppToolbar(
                onBackClick = onBackClick,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFFFFFFF))
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter
        ) {
            when (state) {
                is ConferenceDetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                is ConferenceDetailUiState.Error -> {
                    Text(
                        text = state.message,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is ConferenceDetailUiState.Success -> {
                    Column(
                        modifier = Modifier
                            .width(328.dp)
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ConferenceHeader(state.conference)
                        ConferenceInfoSection(state.conference)
                        ConferenceAboutSection(state.conference.about ?: "")
                    }
                }
            }
        }
    }
}
