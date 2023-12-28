package com.bbj.internshiptest.data.db.model

import com.bbj.internshiptest.data.api.model.Camera
import com.bbj.internshiptest.data.api.model.Door
import com.bbj.internshiptest.ui.model.UICamera
import com.bbj.internshiptest.ui.model.UIDoor

object ToDBMapper {

    fun map(camera: Camera): DBCamera {
        camera.run {
            return DBCamera(
                favorites, id, name, rec, room, snapshot
            )
        }
    }

    fun map(door: Door): DBDoor {
        return DBDoor(door.favorites, door.id, door.name, door.room, door.snapshot)
    }

    fun map(camera: UICamera): DBCamera {
        camera.run {
            return DBCamera(
                favorites, id, name, rec, room, snapshot
            )
        }
    }

    fun map(door: UIDoor): DBDoor {
        return DBDoor(door.favorites, door.id, door.name, door.room, door.snapshot,door.isLocked)
    }
}