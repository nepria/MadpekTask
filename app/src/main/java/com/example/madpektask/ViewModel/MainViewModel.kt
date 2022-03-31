package com.example.madpektask.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madpektask.model.avatar
import com.example.madpektask.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel(){
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMembers()
        }
    }
    val avatar: LiveData<avatar>
        get() = repository.avatar
}