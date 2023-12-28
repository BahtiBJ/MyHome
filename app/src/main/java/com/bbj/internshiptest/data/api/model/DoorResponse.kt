package com.bbj.internshiptest.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class DoorResponse(
    val data: List<Door>,
    val success: Boolean
)