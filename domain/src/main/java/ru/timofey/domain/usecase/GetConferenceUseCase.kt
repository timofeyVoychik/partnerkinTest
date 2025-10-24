package ru.timofey.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.timofey.domain.model.Conference
import ru.timofey.domain.repository.ConferenceRepository
import ru.timofey.domain.repository.ResultWithDomain

class GetConferenceUseCase(
    private val repository: ConferenceRepository
) {
    operator fun invoke(conferenceId: Long): Flow<ResultWithDomain<Conference>> {
        return repository.observeConferenceById(conferenceId)
    }
}
