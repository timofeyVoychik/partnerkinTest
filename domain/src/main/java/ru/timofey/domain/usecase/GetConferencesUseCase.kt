package ru.timofey.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.timofey.domain.model.Conference
import ru.timofey.domain.repository.ConferenceRepository
import ru.timofey.domain.repository.ResultWithDomain

class GetConferencesUseCase(
    private val repository: ConferenceRepository
) {
    operator fun invoke(): Flow<ResultWithDomain<List<Conference>>> {
        return repository.observeConferences()
    }
}
