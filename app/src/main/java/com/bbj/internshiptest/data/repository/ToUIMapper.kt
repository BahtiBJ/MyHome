package com.bbj.internshiptest.data.repository

import com.bbj.internshiptest.data.api.model.Camera
import com.bbj.internshiptest.data.api.model.Door
import com.bbj.internshiptest.data.db.model.DBCamera
import com.bbj.internshiptest.data.db.model.DBDoor
import com.bbj.internshiptest.ui.model.UICamera
import com.bbj.internshiptest.ui.model.UIDoor

object ToUIMapper {

    fun map(camera: Camera): UICamera {
        camera.run {
            return UICamera(
                favorites, id, name, rec, room, snapshot
            )
        }
    }

    fun map(door: Door): UIDoor {
        return UIDoor(door.favorites, door.id, door.name, door.room, door.snapshot, true)
    }

    fun map(camera: DBCamera): UICamera {
        camera.run {
            return UICamera(
                favorites, id, name, rec, room, snapshot
            )
        }
    }

    fun map(door: DBDoor): UIDoor {
        return UIDoor(door.favorites, door.id, door.name, door.room, door.snapshot, door.isLocked)
    }



}