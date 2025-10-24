package ru.timofey.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.timofey.domain.model.Conference

interface ConferenceRepository {
    fun observeConferences(): Flow<ResultWithDomain<List<Conference>>>

    fun observeConferenceById(id: Long): Flow<ResultWithDomain<Conference>>
}
