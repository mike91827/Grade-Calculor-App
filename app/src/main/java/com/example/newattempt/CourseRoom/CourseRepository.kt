package com.example.gradecalculatorrevision.CourseRoom

import androidx.lifecycle.LiveData
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection


//our repository for Courses
class CourseRepository(private val courseDao: CourseDao) {
    val realAllData: LiveData<List<Course>> =  courseDao.readAllData()


    suspend fun addCourse(course: Course){
        courseDao.addCourse(course)
    }

    suspend fun updateCourse(course: Course){
        courseDao.updateCourse(course)
    }

    fun updatePercentCurrent(id:String,current:String){
        courseDao.updatePercentCurrent(id,current)
    }

    fun updatePercentLeft(id:String,left:String){
        courseDao.updatePercentLeft(id,left)
    }

    suspend fun deleteCourse(course: Course){
        courseDao.deleteCourse(course)
    }


   suspend fun deleteAll(){
        courseDao.deleteAll()
    }


}