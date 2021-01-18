package com.example.gradecalculatorrevision.CourseSectionRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionDatabase
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionRepository
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//view model for course sections
class CourseSectionViewModel(application: Application): AndroidViewModel(application)  {

    val readAllData: LiveData<List<CourseSection>>
    private val repository: CourseSectionRepository

    init {
        val courseSectionDao= CourseSectionDatabase.getDatabase(
            application
        ).courseSectionDao()
        repository=
                CourseSectionRepository(
                        courseSectionDao
                )
        readAllData=repository.realAllData
    }

    fun addCourseSection(courseSection: CourseSection){
        viewModelScope.launch (Dispatchers.IO){
            repository.addCourseSection(courseSection)
        }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
    fun updateCourseSection(courseSection: CourseSection) {
        viewModelScope.launch (Dispatchers.IO){
            repository.updateCourseSection(courseSection)

        }
    }


    fun deleteCourseSection(courseSection: CourseSection){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteCourseSection(courseSection)

        }
    }


}