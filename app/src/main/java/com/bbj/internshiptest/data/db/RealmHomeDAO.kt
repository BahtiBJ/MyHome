package com.bbj.internshiptest.data.db

import android.util.Log
import com.bbj.internshiptest.data.db.model.DBCamera
import com.bbj.internshiptest.data.db.model.DBDoor
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

object RealmHomeDAO {

    private val config = RealmConfiguration.create(schema = setOf(DBCamera::class, DBDoor::class))
    private val realm: Realm = Realm.open(config)

    fun getCameras(): List<DBCamera> {
        val foundCameras = realm.query<DBCamera>().find()
        Log.d(this.javaClass.simpleName,"Camera from DB $foundCameras")
        return foundCameras
    }

    fun getDoors(): List<DBDoor> {
        val foundDoors = realm.query<DBDoor>().find()
        Log.d(this.javaClass.simpleName,"Door from DB $foundDoors")
        return foundDoors
    }

    fun getFlowCameras() : Flow<ResultsChange<DBCamera>> {
        return realm.query<DBCamera>().find().asFlow()
    }

    fun getFlowDoors() : Flow<ResultsChange<DBDoor>> {
        return realm.query<DBDoor>().find().asFlow()
    }

    fun insertCamera(dbCamera: DBCamera) {
        realm.writeBlocking {
            copyToRealm(dbCamera)
        }
    }

    fun insertDoor(dbDoor: DBDoor) {
        realm.writeBlocking {
            copyToRealm(dbDoor)
        }
    }

    fun insertCamera(dbCameraList: List<DBCamera>) {
        realm.writeBlocking {
            for (dbCamera in dbCameraList)
                copyToRealm(dbCamera)
        }
    }

    fun insertDoor(dbDoorList: List<DBDoor>) {
        realm.writeBlocking {
            for (dbDoor in dbDoorList)
                copyToRealm(dbDoor)
        }
    }

    fun updateCamera(dbCamera: DBCamera) {
        val foundDbCamera = realm.query<DBCamera>("id == $0" ,dbCamera.id).find()
        realm.writeBlocking {
            findLatest(foundDbCamera[0])?.apply {
                favorites = dbCamera.favorites
                rec = dbCamera.rec
                name = dbCamera.name
            }
        }
    }

    fun updateDoor(dbDoor: DBDoor) {
        val foundDbDoor = realm.query<DBDoor>("id == $0",dbDoor.id).find()
        realm.writeBlocking {
            findLatest(foundDbDoor[0])?.apply {
                favorites = dbDoor.favorites
                isLocked = dbDoor.isLocked
                name = dbDoor.name
            }
        }
    }

}