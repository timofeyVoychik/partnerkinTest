package ru.timofey.feature_list.model

import ru.timofey.core_ui.model.UiConference

sealed class ConferenceListUiState {
    object Loading : ConferenceListUiState()
    data class Error(val message: String) : ConferenceListUiState()
    data class Success(
        val groupedConferences: Map<String, List<UiConference>>
    ) : ConferenceListUiState()
}
