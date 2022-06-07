package com.example.tmdb.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrewResponse(
    @SerialName("name")
    val name: String,
    @SerialName("job")
    val job: String,
    @SerialName("profile_path")
    val profilePath: String?
)
