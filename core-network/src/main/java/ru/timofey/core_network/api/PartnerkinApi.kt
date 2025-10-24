package ru.timofey.core_network.api

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.timofey.core_network.NetworkClient
import ru.timofey.core_network.model.NetworkConferenceDetailResponse
import ru.timofey.core_network.model.NetworkConferenceListResponse
import ru.timofey.core_network.util.NetworkConstants

class PartnerkinApi {

    private val client = NetworkClient.client

    suspend fun getConferenceList(): NetworkConferenceListResponse {
        return client.get("${NetworkConstants.BASE_URL}/list") {
            parameter("api_key", NetworkConstants.API_KEY)
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getConferenceDetail(): NetworkConferenceDetailResponse {
        return client.get("${NetworkConstants.BASE_URL}/view") {
            parameter("api_key", NetworkConstants.API_KEY)
            contentType(ContentType.Application.Json)
        }.body()
    }
}
