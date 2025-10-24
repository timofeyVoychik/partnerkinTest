package ru.timofey.feature_list.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.timofey.domain.repository.ResultWithDomain
import ru.timofey.domain.usecase.GetConferencesUseCase
import ru.timofey.feature_list.model.ConferenceListUiState
import ru.timofey.core_ui.model.UiConference
import kotlinx.datetime.*
import ru.timofey.core_ui.model.UiCategory
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ConferenceListViewModel @Inject constructor(
    private val getConferencesUseCase: GetConferencesUseCase
) : ViewModel() {

    private val monthNamesRu = listOf(
        "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
        "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    )


    private val _uiState = MutableStateFlow<ConferenceListUiState>(ConferenceListUiState.Loading)
    val uiState: StateFlow<ConferenceListUiState> = _uiState

    init {
        observeConferences()
    }


    private fun observeConferences() {
        viewModelScope.launch {
            getConferencesUseCase().collectLatest { result ->
                when (result) {
                    is ResultWithDomain.Loading -> _uiState.value = ConferenceListUiState.Loading

                    is ResultWithDomain.Success -> {
                        val uiList = result.data.map { conf ->
                            val startDate: LocalDate? = conf.startDate
                            val endDate: LocalDate? = conf.endDate

                            val categories = conf.categories.map {
                                UiCategory(it.id, it.name, it.url)
                            }

                            UiConference(
                                id = conf.id,
                                name = conf.name,
                                city = conf.city,
                                country = conf.country,
                                imageUrl = conf.image?.url,
                                rating = conf.rating,
                                startDate = startDate,
                                endDate = endDate,
                                status = conf.status,
                                tags = conf.categories.map { it.name },
                                isCancelled = conf.status == "canceled",
                                about = conf.about,
                                registerUrl = conf.registerUrl,
                                categories = categories,
                            )
                        }

                        val grouped = uiList
                            .sortedBy { it.startDate }
                            .groupBy { conf ->
                                val date = conf.startDate ?: LocalDate(1970, 1, 1)
                                val monthName = monthNamesRu[date.monthNumber - 1]
                                "$monthName, ${date.year}"
                            }

                        _uiState.value = ConferenceListUiState.Success(grouped)
                    }

                    is ResultWithDomain.Error -> {
                        _uiState.value = ConferenceListUiState.Error(
                            result.error.message ?: "Неизвестная ошибка"
                        )
                    }
                }
            }
        }
    }
}
