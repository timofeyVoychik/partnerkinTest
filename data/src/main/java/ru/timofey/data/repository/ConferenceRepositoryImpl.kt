package ru.timofey.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.timofey.core_network.api.PartnerkinApi
import ru.timofey.data.mapper.toDomain
import ru.timofey.domain.model.Conference
import ru.timofey.domain.repository.ConferenceRepository
import ru.timofey.domain.repository.ResultWithDomain
import ru.timofey.domain.util.DomainError
import javax.inject.Inject
import ru.timofey.core_network.checker.NetworkChecker

class ConferenceRepositoryImpl @Inject constructor(
    private val api: PartnerkinApi,
    private val localRepository: LocalConferenceRepository,
    private val networkChecker: NetworkChecker
) : ConferenceRepository {

    override fun observeConferences(): Flow<ResultWithDomain<List<Conference>>> = flow {
        emit(ResultWithDomain.Loading)
        if (networkChecker.isNetworkAvailable()) {
            try {
                val response = api.getConferenceList()
                val items = response.data?.result
                    ?.mapNotNull { it.conference.toDomain() }
                    ?.sortedBy { it.startDate }

                if (items != null) {
                    localRepository.insertConferences(items)
                    emit(ResultWithDomain.Success(items))
                } else {
                    emit(ResultWithDomain.Error(DomainError.ParsingError()))
                }
            } catch (e: Exception) {
                emit(localRepository.getConferences())
            }
        } else {
            emit(localRepository.getConferences())
        }
    }


    override fun observeConferenceById(id: Long): Flow<ResultWithDomain<Conference>> = flow {
        emit(ResultWithDomain.Loading)

        try {
            if (networkChecker.isNetworkAvailable()) {
                val response = api.getConferenceDetail()
                val data = response.data?.toDomain()

                if (data != null) {
                    localRepository.insertConferences(listOf(data))
                    emit(ResultWithDomain.Success(data))
                } else {
                    emit(localRepository.getConferenceById(id))
                }
            } else {
                emit(localRepository.getConferenceById(id))
            }

        } catch (e: Exception) {
            emit(localRepository.getConferenceById(id))
        }
    }




}
