package ru.timofey.data.repository

import ru.timofey.core_db.dao.ConferenceDao
import ru.timofey.core_db.mappers.toDomain
import ru.timofey.core_db.mappers.toEntity
import ru.timofey.domain.model.Conference
import ru.timofey.domain.repository.ResultWithDomain
import ru.timofey.domain.util.DomainError
import javax.inject.Inject

class LocalConferenceRepository @Inject constructor(
    private val dao: ConferenceDao
) {

    suspend fun getConferences(): ResultWithDomain<List<Conference>> = try {
        val list = dao.getConferences().map { it.toDomain() }
        if (list.isNotEmpty()) ResultWithDomain.Success(list)
        else ResultWithDomain.Error(DomainError.NotFound(0))
    } catch (e: Exception) {
        ResultWithDomain.Error(DomainError.Unknown(e))
    }

    suspend fun getConferenceById(id: Long): ResultWithDomain<Conference> = try {
        val item = dao.getConferenceById(id)
        if (item != null) ResultWithDomain.Success(item.toDomain())
        else ResultWithDomain.Error(DomainError.NotFound(id))
    } catch (e: Exception) {
        ResultWithDomain.Error(DomainError.Unknown(e))
    }

    suspend fun insertConferences(conferences: List<Conference>) {
        dao.insertConferences(conferences.map { it.toEntity() })
        conferences.forEach { conf ->
            dao.insertCategories(conf.categories.map { it.toEntity(conf.id) })
            conf.image?.let { dao.insertImages(listOf(it.toEntity(conf.id))) }
        }
    }
}
