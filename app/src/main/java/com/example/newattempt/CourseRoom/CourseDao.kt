package com.example.gradecalculatorrevision.CourseRoom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection


//all the methods in this Dao are what we can use for the Course database
//all their names are self-explanatory
@Dao
interface CourseDao {

    @Query("DELETE FROM course_table")
    fun deleteAll()


    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun addCourse(course: Course)



    @Delete
    suspend fun deleteCourse(course: Course)

    @Query ("UPDATE course_table SET percentCurrent=:current WHERE title=:id")
    fun updatePercentCurrent(id:String,current:String)


    @Query ("UPDATE course_table SET percentLeft=:left WHERE title=:id")
    fun updatePercentLeft(id:String,left:String)


    @Update
    suspend fun updateCourse(course: Course)

    @Query("SELECT * FROM course_table ORDER BY title ASC")
    fun readAllData():LiveData<List<Course>>


}