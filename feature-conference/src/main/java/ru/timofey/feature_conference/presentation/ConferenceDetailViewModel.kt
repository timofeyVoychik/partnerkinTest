package ru.timofey.feature_conference.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.timofey.core_ui.model.UiCategory
import ru.timofey.domain.usecase.GetConferenceUseCase
import ru.timofey.feature_conference.model.ConferenceDetailUiState
import ru.timofey.core_ui.model.UiConference
import ru.timofey.domain.repository.ResultWithDomain
import javax.inject.Inject

@HiltViewModel
class ConferenceDetailViewModel @Inject constructor(
    private val getConferenceDetailUseCase: GetConferenceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ConferenceDetailUiState>(ConferenceDetailUiState.Loading)
    val uiState: StateFlow<ConferenceDetailUiState> = _uiState

    fun loadConference(id: Long) {
        viewModelScope.launch {
            getConferenceDetailUseCase(id)
                .catch { e ->
                    _uiState.value = ConferenceDetailUiState.Error(e.message ?: "Unknown error")
                }
                .collect { result ->
                    when (result) {
                        is ResultWithDomain.Loading -> _uiState.value = ConferenceDetailUiState.Loading
                        is ResultWithDomain.Error -> _uiState.value =
                            ConferenceDetailUiState.Error(result.error.message ?: "Error")
                        is ResultWithDomain.Success -> {
                            val c = result.data
                            val categories = c.categories.map {
                                UiCategory(it.id, it.name, it.url)
                            }
                            val ui = UiConference(
                                id = c.id,
                                name = c.name,
                                city = c.city,
                                country = c.country,
                                imageUrl = c.image?.url,
                                rating = c.rating,
                                startDate = c.startDate,
                                endDate = c.endDate,
                                status = c.status,
                                about = c.about,
                                registerUrl = c.registerUrl,
                                categories = categories
                            )
                            _uiState.value = ConferenceDetailUiState.Success(ui)
                        }
                    }
                }
        }
    }
}
