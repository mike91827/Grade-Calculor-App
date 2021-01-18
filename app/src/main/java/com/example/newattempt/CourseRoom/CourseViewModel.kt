package com.example.gradecalculatorrevision.CourseRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//our viewModel for all our courses. We invoke all the methods from the CourseViewModel in this project
class CourseViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Course>>
    private val repository: CourseRepository

    init {
        val courseDao= CourseDatabase.getDatabase(
            application
        ).courseDao()
        repository=
                CourseRepository(
                        courseDao
                )
        readAllData=repository.realAllData
    }

    fun addCourse(course: Course){
        viewModelScope.launch (Dispatchers.IO){
            repository.addCourse(course)
        }
    }

    fun updateCourse(course: Course){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateCourse(course)

        }
    }
    fun deleteCourse(course: Course){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteCourse(course)

        }

    }



    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
    fun updatePercentCurrent(id:String,current:String){
        viewModelScope.launch (Dispatchers.IO){
            repository.updatePercentCurrent(id,current)
        }
    }

    fun updatePercentLeft(id:String,left:String){
        viewModelScope.launch (Dispatchers.IO){
            repository.updatePercentLeft(id,left)
        }
    }



}