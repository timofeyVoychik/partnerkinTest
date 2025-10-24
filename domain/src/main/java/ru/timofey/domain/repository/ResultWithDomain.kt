package ru.timofey.domain.repository


import ru.timofey.domain.util.DomainError

sealed class ResultWithDomain<out T> {
    data class Success<T>(val data: T) : ResultWithDomain<T>()
    data class Error(val error: DomainError) : ResultWithDomain<Nothing>()
    data object Loading : ResultWithDomain<Nothing>()
}
