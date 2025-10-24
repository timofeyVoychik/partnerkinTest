package ru.timofey.domain.util

sealed class DomainError(message: String? = null, cause: Throwable? = null) : Throwable(message, cause) {
    class NetworkError(cause: Throwable? = null) : DomainError("Network error", cause)
    class NotFound(val id: Long) : DomainError("Not found: $id")
    class ParsingError(cause: Throwable? = null) : DomainError("Parsing error", cause)
    class Unknown(cause: Throwable? = null) : DomainError("Unknown error", cause)
}