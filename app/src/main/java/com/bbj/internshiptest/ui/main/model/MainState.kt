package com.bbj.internshiptest.ui.main.model

import com.bbj.internshiptest.ui.model.UICamera
import com.bbj.internshiptest.ui.model.UIDoor
import com.bbj.internshiptest.ui.screen.home.model.Section

sealed class MainState() {

    object Loading : MainState()

    object Initial : MainState()

    class Error(val error : Exception) : MainState()

    class CameraSection(val cameraList: List<UICamera>) : MainState()

    class DoorSection(val doorList: List<UIDoor>) : MainState()

}
