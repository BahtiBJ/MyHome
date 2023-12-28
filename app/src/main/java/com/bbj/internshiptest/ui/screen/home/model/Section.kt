package com.bbj.internshiptest.ui.screen.home.model

import androidx.annotation.StringRes
import com.bbj.internshiptest.R

enum class Section (@StringRes val sectionNameRes : Int) {
    CAMERA(R.string.cameras),
    DOOR(R.string.doors)
}