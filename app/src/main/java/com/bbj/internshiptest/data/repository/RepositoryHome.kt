package com.bbj.internshiptest.data.repository

import com.bbj.internshiptest.data.api.KtorInstance
import com.bbj.internshiptest.data.db.RealmHomeDAO
import com.bbj.internshiptest.data.db.model.ToDBMapper
import com.bbj.internshiptest.ui.model.UICamera
import com.bbj.internshiptest.ui.model.UIDoor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object RepositoryHome {

    suspend fun getCameras(): Flow<List<UICamera>> {
        val cameraFromDB = RealmHomeDAO.getCameras()
        if (cameraFromDB.isEmpty()){
            val cameraResponse = KtorInstance.getCameras()
            RealmHomeDAO.insertCamera(cameraResponse.data.cameras.map {
                ToDBMapper.map(it)
            })
        }
        return RealmHomeDAO.getFlowCameras().map {
            it.list.map {
                ToUIMapper.map(it)
            }
        }
    }

    suspend fun getDoors() : Flow<List<UIDoor>> {
        val doorFromDB = RealmHomeDAO.getDoors()
        if (doorFromDB.isEmpty()){
            val doorResponse = KtorInstance.getDoors()
            RealmHomeDAO.insertDoor(doorResponse.data.map {
                ToDBMapper.map(it)
            })
        }
        return RealmHomeDAO.getFlowDoors().map {
            it.list.map {
                ToUIMapper.map(it)
            }
        }
    }

    fun updateCamera(camera : UICamera) {
        RealmHomeDAO.updateCamera(ToDBMapper.map(camera))
    }

    fun updateDoor(door : UIDoor) {
        RealmHomeDAO.updateDoor(ToDBMapper.map(door))
    }

}