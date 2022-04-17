package com.example.firstactivity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("/user/login")
    fun gotoLogin(@Query("account")account:String,@Query("password")password:String): Call<HttpResult<Boolean>>
}