package com.example.newattempt.SchoolWorkRoom

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


//the school work is similar to a course section. Represents thing's like assignments, tests, quizzes, etc.
//THings that give you marks in a course
@Parcelize
@Entity
    (tableName = "school_work_table")
data class SchoolWork (
    @PrimaryKey(autoGenerate = false)
    val title:String,
    //amount of items their are in the course
    val amount:Int,

    //how much these items are worth in the course
    val percentTotal:Double,
    //how much percent they earned from this school work (through marks)
    val percentEarned:Double,
    //how much percent they have left to earn in this school work
    val percentLeft:Double,
    //the corresspondding course title
    val courseTitle:String

):Parcelable