package com.example.newattempt.SchoolWorkRoom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SchoolWorkDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun  addSchoolWork(schoolWork: SchoolWork)

    @Delete
    suspend fun deleteSchoolWork(schoolWork: SchoolWork)

    @Update
    suspend fun updateSchoolWork(schoolWork: SchoolWork)


    @Query("DELETE FROM school_work_table")
    fun deleteAll()

    @Query("SELECT * FROM school_work_table ORDER BY title ASC")
    fun readAllData(): LiveData<List<SchoolWork>>


    @Query ("UPDATE school_work_table SET percentEarned=:earned WHERE title=:id")
    fun updatePercentEarned(id:String,earned:Double)

    @Query ("UPDATE school_work_table SET percentLeft=:left WHERE title=:id")
    fun updatePercentLeft(id:String,left:Double)




}