package com.bbj.internshiptest.data.db.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId


class DBDoor(
    var favorites: Boolean,
    var id: Int,
    var name: String,
    var room: String? = null,
    var snapshot: String? = null,
    var isLocked : Boolean = true,
    @PrimaryKey
    var primaryId : ObjectId = ObjectId(),
) : RealmObject {
    constructor() : this (
        false,
        -1,
        "",
        null,
        null,
        true,
        ObjectId()
    )
}