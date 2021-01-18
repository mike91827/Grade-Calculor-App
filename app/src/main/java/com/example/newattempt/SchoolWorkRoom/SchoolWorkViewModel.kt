package com.example.newattempt.SchoolWorkRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolWorkViewModel(application: Application) : AndroidViewModel(application){
    val readAllData: LiveData<List<SchoolWork>>
    private val repository: SchoolWorkRepository
   // lateinit var sc:SchoolWork

    init {
        val schoolWorkDao= SchoolWorkDatabase.getDatabase(
            application
        ).schoolWorkDao()
        repository= SchoolWorkRepository(schoolWorkDao)
        readAllData=repository.realAllData
    }

    fun addSchoolWork(schoolWork: SchoolWork){
        viewModelScope.launch (Dispatchers.IO){
            repository.addSchoolWork(schoolWork)
        }
    }

    fun deleteSchoolWork(schoolWork: SchoolWork){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteSchoolWork(schoolWork)
        }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun updateSchoolWork(schoolWork: SchoolWork){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateSchoolWork(schoolWork)
        }
    }

    fun updatePercentEarned(id:String,earned:Double){
        viewModelScope.launch (Dispatchers.IO){
            repository.updatePercentEarned(id,earned)
        }
    }

    fun updatePercentLeft(id:String,left:Double){
        viewModelScope.launch (Dispatchers.IO){
            repository.updatePercentLeft(id,left)
        }
    }


}