package com.example.gradecalculatorrevision.CourseSectionRoom

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//database for course section
@Database(entities=[CourseSection::class],version=3,exportSchema = false)
abstract class CourseSectionDatabase:RoomDatabase() {
    abstract fun courseSectionDao():CourseSectionDao


    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
        }
    }
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }

    companion object{
        //prevents multiple instances
        @Volatile
        private var INSTANCE: CourseSectionDatabase?=null

        fun getDatabase(context: Context): CourseSectionDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                Log.d("myTag","course empty")
                return tempInstance
            }
            synchronized(this){
                Log.d("myTag","course full")
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    CourseSectionDatabase::class.java,
                    "course_section_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE= instance
                return instance
            }

        }

    }
}