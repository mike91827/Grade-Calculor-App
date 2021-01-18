package com.example.newattempt.SchoolWorkExtensionRoom

import androidx.lifecycle.LiveData
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionDao

class SchoolWorkExtensionRepository(private val schoolWorkExtensionDao:SchoolWorkExtensionDao)
{

    val readAllData:LiveData<List<SchoolWorkExtension>> = schoolWorkExtensionDao.readAllData()


    suspend fun addSchoolWorkExtension(schoolWorkExtension: SchoolWorkExtension){
        schoolWorkExtensionDao.addSchoolWorkExtension(schoolWorkExtension)
    }

    suspend fun updateSchoolWorkExtension(schoolWorkExtension: SchoolWorkExtension){
        schoolWorkExtensionDao.updateSchoolWorkExtension(schoolWorkExtension)
    }
    fun deleteAll(){
        schoolWorkExtensionDao.deleteAll()
    }


}