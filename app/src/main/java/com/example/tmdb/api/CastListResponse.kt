package com.example.tmdb.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastListResponse(
    @SerialName("cast")
    val cast: List<CastResponse>,
    @SerialName("crew")
    val crew: List<CrewResponse>

)
