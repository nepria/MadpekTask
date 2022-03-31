package com.example.madpektask.api

import com.example.madpektask.model.avatar
import retrofit2.Response
import retrofit2.http.GET

interface AvatarService {
    @GET("users?page=1")
    suspend fun getMembers(
    ): Response<avatar>

}