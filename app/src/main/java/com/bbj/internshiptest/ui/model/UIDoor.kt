package com.bbj.internshiptest.ui.model

data class UIDoor(
    var favorites: Boolean,
    val id: Int,
    var name: String,
    val room: String?,
    val snapshot: String?,
    var isLocked : Boolean
)
