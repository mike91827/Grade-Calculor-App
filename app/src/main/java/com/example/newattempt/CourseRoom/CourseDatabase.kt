package com.example.gradecalculatorrevision.CourseRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionDatabase


//database for our course. Stores all the courses we have
//code does not matter to much here since it is the same across all databases
@Database (entities=[Course::class,CourseSection::class],version=3)
abstract class CourseDatabase:RoomDatabase() {



    abstract fun courseDao():CourseDao

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
        private var INSTANCE: CourseDatabase?=null

        fun getDatabase(context: Context): CourseDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    CourseDatabase::class.java,
                "course_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE= instance
                return instance
            }

        }

    }
}