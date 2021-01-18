package com.example.gradecalculatorrevision.CourseRoom

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


//This course class represents all the courses the user is taking.

@Parcelize
@Entity
    (tableName = "course_table")
data class Course (
    @PrimaryKey(autoGenerate = false)
    //the name of the course. Each name must be unique
    val title:String,
    //how much percent was earned in the class
    val percentCurrent:String,
    //how much percent left the user can earn
    val percentLeft:String


):Parcelable
