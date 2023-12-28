package com.bbj.internshiptest.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CameraAndRoom(
    val cameras: List<Camera>,
    val room: List<String>
)