package com.example.myapplication.networking

import com.example.myapplication.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JobApi {


    companion object {

        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(MainActivity.baseURL)
                            .build()
                }
                return retrofit!!
            }
    }
}