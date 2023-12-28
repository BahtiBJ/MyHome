package com.bbj.internshiptest.data.api

import android.util.Log
import com.bbj.internshiptest.data.api.model.CameraAndRoomResponse
import com.bbj.internshiptest.data.api.model.DoorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val BASE_URL = "http://cars.cprogroup.ru/api/rubetek/"
const val CAMERA_ROUTE = "cameras/"
const val DOOR_ROUTE = "doors/"


object KtorInstance {

    private val httpClient = HttpClient(){
        install(HttpTimeout) {
            requestTimeoutMillis = 1000
        }
        install(ContentNegotiation){
            json(Json() {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCameras(): CameraAndRoomResponse {
        Log.d(this.javaClass.simpleName,httpClient.get(BASE_URL + CAMERA_ROUTE).body<String>().toString())
        return httpClient.get(BASE_URL + CAMERA_ROUTE).body()
    }

    suspend fun getDoors(): DoorResponse {
        return httpClient.get(BASE_URL + DOOR_ROUTE).body()
    }

}