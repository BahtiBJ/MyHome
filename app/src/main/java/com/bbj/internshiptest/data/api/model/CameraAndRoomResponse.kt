package com.bbj.internshiptest.data.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class CameraAndRoomResponse(
    val data: CameraAndRoom,
    val success: Boolean
)