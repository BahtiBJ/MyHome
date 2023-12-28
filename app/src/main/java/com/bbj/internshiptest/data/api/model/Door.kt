package com.bbj.internshiptest.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Door(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val room: String? = null,
    val snapshot: String? = null
)