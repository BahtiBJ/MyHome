package com.bbj.internshiptest.data.db.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class DBCamera(
    var favorites: Boolean,
    var id: Int,
    var name: String,
    var rec: Boolean,
    var room: String? = null,
    var snapshot: String? = null,
    @PrimaryKey
    var primaryId : ObjectId = ObjectId(),
) : RealmObject {
    constructor() : this (
        false,
        -1,
        "",
        false,
        null,
        null,
        ObjectId()
    )
}