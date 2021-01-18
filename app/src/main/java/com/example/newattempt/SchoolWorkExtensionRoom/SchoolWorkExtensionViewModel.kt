package com.example.newattempt.SchoolWorkExtensionRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionDatabase
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolWorkExtensionViewModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<SchoolWorkExtension>>
    private val repository: SchoolWorkExtensionRepository


    init {
        val schoolWorkExtensionDao= SchoolWorkExtensionDatabase.getDatabase(
            application
        ).schoolWorkExtensionDao()
        repository=
            SchoolWorkExtensionRepository(
                schoolWorkExtensionDao
            )
        readAllData=repository.readAllData
    }


    fun addSchoolWorkExtension(schoolWorkExtension: SchoolWorkExtension){
        viewModelScope.launch (Dispatchers.IO){
            repository.addSchoolWorkExtension(schoolWorkExtension)
        }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
    fun updateSchoolWorkExtension(schoolWorkExtension: SchoolWorkExtension){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateSchoolWorkExtension(schoolWorkExtension)
        }
    }

}