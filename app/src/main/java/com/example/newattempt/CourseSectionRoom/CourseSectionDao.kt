package com.example.gradecalculatorrevision.CourseSectionRoom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gradecalculatorrevision.CourseRoom.Course

//the Dao for the course section
@Dao
interface CourseSectionDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addCourseSection(courseSection: CourseSection)


    @Query("DELETE FROM course_section_table")
    fun deleteAll()


    @Update
    suspend fun updateCourseSection(courseSection: CourseSection)

    @Delete
    suspend fun deleteCourseSection(courseSection: CourseSection)

    @Query("SELECT * FROM course_section_table ORDER BY title ASC")
    fun readAllData(): LiveData<List<CourseSection>>
}