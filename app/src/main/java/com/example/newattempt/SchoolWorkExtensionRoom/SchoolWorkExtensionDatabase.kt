package com.example.newattempt.SchoolWorkExtensionRoom

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newattempt.SchoolWorkRoom.SchoolWork
import com.example.newattempt.SchoolWorkRoom.SchoolWorkDao
import com.example.newattempt.SchoolWorkRoom.SchoolWorkDatabase

@Database(entities = [SchoolWorkExtension::class],version=1,exportSchema = false)
abstract class SchoolWorkExtensionDatabase:RoomDatabase() {

    abstract fun schoolWorkExtensionDao(): SchoolWorkExtensionDao


    companion object{
        @Volatile
        private var INSTANCE: SchoolWorkExtensionDatabase? = null

        fun getDatabase(context: Context): SchoolWorkExtensionDatabase {
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    SchoolWorkExtensionDatabase::class.java,
                    "school_work_extension_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}