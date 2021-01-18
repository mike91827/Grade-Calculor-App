package com.example.newattempt.SchoolWorkRoom

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionDatabase

@Database (entities = [SchoolWork::class],version=1,exportSchema = false)
abstract class SchoolWorkDatabase : RoomDatabase(){
    abstract fun schoolWorkDao():SchoolWorkDao

    companion object{
        @Volatile
        private var INSTANCE: SchoolWorkDatabase? = null

        fun getDatabase(context: Context):SchoolWorkDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                Log.d("myTag","cschoolempty")
                return tempInstance
            }
            synchronized(this){
                Log.d("myTag","school full")
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    SchoolWorkDatabase::class.java,
                    "school_work_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}