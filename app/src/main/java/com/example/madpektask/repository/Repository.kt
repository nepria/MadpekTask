package com.example.madpektask.repository

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madpektask.MainActivity
import com.example.madpektask.api.AvatarService
import com.example.madpektask.api.RetrofitHelper
import com.example.madpektask.model.avatar
import java.lang.Exception


class Repository(private val avatarService: AvatarService) {
    private val avatarLiveData = MutableLiveData<avatar>()
    val avatar: LiveData<avatar>
        get() = avatarLiveData

    suspend fun getMembers() {
        val result =  RetrofitHelper.API.getMembers()
        when {
            result.body() != null -> {
                avatarLiveData.postValue(result.body())
            }
            !result.isSuccessful -> {
               Log.e("Error", "Api not fetched")
            }

        }

    }
}