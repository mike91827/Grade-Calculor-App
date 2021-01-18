package com.example.gradecalculatorrevision.CourseSectionRoom

import androidx.lifecycle.LiveData
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionDao
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection


//repository for course sections
class CourseSectionRepository(private val courseSectionDao: CourseSectionDao) {

    val realAllData: LiveData<List<CourseSection>> =  courseSectionDao.readAllData()

    suspend fun addCourseSection(courseSection: CourseSection){
        courseSectionDao.addCourseSection(courseSection)
    }

    suspend fun deleteAll(){
        courseSectionDao.deleteAll()
    }
    suspend fun updateCourseSection(courseSection: CourseSection){
        courseSectionDao.updateCourseSection(courseSection)
    }

    suspend fun deleteCourseSection(courseSection: CourseSection){
        courseSectionDao.deleteCourseSection(courseSection)
    }
}