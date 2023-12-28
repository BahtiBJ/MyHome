package com.bbj.internshiptest.ui.main.model

import com.bbj.internshiptest.ui.model.UICamera
import com.bbj.internshiptest.ui.model.UIDoor
import com.bbj.internshiptest.ui.screen.home.model.Section

sealed class MainEvent {

    class ChangeSectionEvent(val section: Section) : MainEvent()

    class AddToFavoriteDoorEvent(val door: UIDoor) : MainEvent()

    class AddToFavoriteCameraEvent(val camera: UICamera) : MainEvent()

    class ChangeDoorNameEvent(val door: UIDoor) : MainEvent()

    class LoadData(val section: Section) : MainEvent()

}
