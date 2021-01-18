package com.example.newattempt.SchoolWorkRoom

import androidx.lifecycle.LiveData
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionDao

class SchoolWorkRepository (private val schoolWorkDao: SchoolWorkDao){

    val realAllData: LiveData<List<SchoolWork>> =  schoolWorkDao.readAllData()

    suspend fun addSchoolWork(schoolWork: SchoolWork){
        schoolWorkDao.addSchoolWork(schoolWork)
    }

    suspend fun updateSchoolWork(schoolWork: SchoolWork){
        schoolWorkDao.updateSchoolWork(schoolWork)
    }

    suspend fun deleteAll(){
        schoolWorkDao.deleteAll()
    }

    suspend fun deleteSchoolWork(schoolWork: SchoolWork){
        schoolWorkDao.deleteSchoolWork(schoolWork)
    }

    suspend fun updatePercentEarned(id:String,earned:Double){
        schoolWorkDao.updatePercentEarned(id,earned)
    }


    fun updatePercentLeft(id:String,left:Double){
        schoolWorkDao.updatePercentLeft(id,left)
    }


}