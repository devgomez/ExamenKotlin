package com.gomez.examenkotlin.services

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiRepository {
    object RetrofitRepository {
        const val BASE_URL = "https://my-json-server.typicode.com/rzkbrian/public_db/"

        fun getService() : ApiService {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(ApiService::class.java)
        }
    }
}