package ru.timofey.feature_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.timofey.core_ui.components.ConferenceCard
import ru.timofey.core_ui.theme.Inter
import ru.timofey.feature_list.model.ConferenceListUiState

@Composable
fun ConferenceListContent(
    state: ConferenceListUiState,
    onConferenceClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is ConferenceListUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ConferenceListUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Ошибка: ${state.message}")
            }
        }
        is ConferenceListUiState.Success -> {
            LazyColumn(
                modifier = modifier.background(Color(0xFFFFFFFF)),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                state.groupedConferences.forEach { (monthHeader, conferences) ->
                    item {
                        Box(
                            modifier = Modifier
                                .padding(top = 16.dp, start = 16.dp)
                                .wrapContentWidth()
                        ) {
                            Text(
                                text = monthHeader,
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color(0xFF0E1234)
                            )
                        }
                    }
                    items(conferences) { conference ->
                        ConferenceCard(
                            conference = conference,
                            onClick = { onConferenceClick(conference.id) }
                        )
                    }
                }
            }
        }
    }
}