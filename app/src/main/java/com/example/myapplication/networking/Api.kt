package com.example.myapplication.networking


import com.example.myapplication.model.Topic
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("getuser")
    fun getUserResult(@Query("userid") userid: String,
                      @Query("loguserid") loguserid: String,
                      @Query("timestampin") timestampin: String): Call<Topic>

}