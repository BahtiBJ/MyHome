package com.bbj.internshiptest.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Camera(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val rec: Boolean,
    val room: String? = null,
    val snapshot: String? = null
)