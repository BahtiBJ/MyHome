package com.bbj.internshiptest.ui.model

data class UICamera(
    var favorites: Boolean,
    val id: Int,
    val name: String,
    val rec: Boolean,
    val room: String?,
    val snapshot: String?
)
