package com.bbj.internshiptest.ui.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbj.internshiptest.data.repository.RepositoryHome
import com.bbj.internshiptest.ui.main.model.MainEvent
import com.bbj.internshiptest.ui.main.model.MainState
import com.bbj.internshiptest.ui.model.UICamera
import com.bbj.internshiptest.ui.model.UIDoor
import com.bbj.internshiptest.ui.screen.home.model.Section
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ViewModelMain : ViewModel() {

    private val _mainState = mutableStateOf<MainState>(MainState.Initial)
    val mainState: State<MainState>
        get() = _mainState

    private val _sectionState = mutableStateOf(Section.CAMERA)
    val sectionState: State<Section>
        get() = _sectionState

    private var cameraFlow : Flow<List<UICamera>>? = null
    var doorFlow : Flow<List<UIDoor>>? = null


    fun obtainEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ChangeSectionEvent -> {
                _sectionState.value = event.section
                if (event.section == Section.DOOR) {
                    getDoors()
                } else {
                    getCameras()
                }
            }
            is MainEvent.AddToFavoriteDoorEvent -> {
                updateDoor(event.door)
            }
            is MainEvent.AddToFavoriteCameraEvent -> {
                updateCamera(event.camera)
            }
            is MainEvent.ChangeDoorNameEvent -> {
                updateDoor(event.door)
            }
            is MainEvent.LoadData -> {
                if (event.section == Section.CAMERA) {
                    getCameras()
                } else {
                    getDoors()
                }
            }
        }
    }

    private fun getCameras() {
        if (sectionState.value != Section.CAMERA)
            return
        viewModelScope.launch(Dispatchers.IO) {
            _mainState.value = MainState.Loading
            try {
                if (cameraFlow == null){
                    cameraFlow = RepositoryHome.getCameras()
                }
                cameraFlow?.collect(){
                    if (sectionState.value != Section.CAMERA)
                        return@collect

                    _mainState.value = MainState.CameraSection(it.sortedBy {
                        it.room
                    })
                }
            } catch (e: Exception) {
                Log.e(this.javaClass.simpleName, e.message, e)
                _mainState.value = MainState.Error(e)
            }

        }
    }

    private fun getDoors() {
        if (sectionState.value != Section.DOOR)
            return
        viewModelScope.launch(Dispatchers.IO) {
            _mainState.value = MainState.Loading
            try {
                if (doorFlow == null){
                    doorFlow = RepositoryHome.getDoors()
                }
                doorFlow?.collect(){
                    if (sectionState.value != Section.DOOR)
                        return@collect

                    _mainState.value = MainState.DoorSection(it.sortedBy {
                        it.room
                    })
                }
            } catch (e: Exception) {
                Log.e(this.javaClass.simpleName, e.message, e)
                _mainState.value = MainState.Error(e)
            }

        }
    }

    private fun updateCamera(camera : UICamera) {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryHome.updateCamera(camera)
        }
    }

    private fun updateDoor(door : UIDoor) {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryHome.updateDoor(door)
        }
    }

}