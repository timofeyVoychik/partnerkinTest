package ru.timofey.core_network.model

data class NetworkConferenceListResponse(
    val error: String?,
    val data: NetworkConferenceListData?
)

data class NetworkConferenceListData(
    val counts: Int?,
    val pagination: NetworkPagination?,
    val result: List<NetworkConferenceListItem>?
)

data class NetworkPagination(
    val page_size: Int?,
    val current_page: Int?
)

data class NetworkConferenceListItem(
    val view_type: Int?,
    val conference: NetworkConference
)
