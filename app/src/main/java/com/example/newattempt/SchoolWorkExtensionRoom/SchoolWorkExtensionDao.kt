package com.example.newattempt.SchoolWorkExtensionRoom

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SchoolWorkExtensionDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addSchoolWorkExtension(schoolWorkExtension: SchoolWorkExtension)

    @Update
    suspend fun updateSchoolWorkExtension(schoolWorkExtension: SchoolWorkExtension)

    @Query("DELETE FROM school_work_extensions_table")
    fun deleteAll()

    @Query("SELECT * FROM school_work_extensions_table WHERE SchoolWorkTitle LIKE :id")
    fun getBySchoolCourse(id: String): LiveData<List<SchoolWorkExtension>>

    @Query("SELECT * FROM school_work_extensions_table ORDER BY title ASC")
    fun readAllData(): LiveData<List<SchoolWorkExtension>>
}