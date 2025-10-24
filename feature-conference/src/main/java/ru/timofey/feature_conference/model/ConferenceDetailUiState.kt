package ru.timofey.feature_conference.model

import ru.timofey.core_ui.model.UiConference

sealed class ConferenceDetailUiState {
    object Loading : ConferenceDetailUiState()
    data class Success(val conference: UiConference) : ConferenceDetailUiState()
    data class Error(val message: String) : ConferenceDetailUiState()
}
