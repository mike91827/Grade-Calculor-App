package com.example.gradecalculatorrevision.CourseSectionRoom

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//represents all the course sections. Basically we have courses. Courses have items that gives us marks
//like asssignment, test, quizzes etc. These items are what course sections are

@Parcelize
@Entity
    (tableName = "course_section_table")
data class CourseSection (
    @PrimaryKey(autoGenerate = false)
    //the name of the course section
    val title:String,
    //how many of this course sections their is
    val amount:Int,
    //how much this course section is worth
    val accountedPercent:Int,
    //their corressponding course
    val courseTitle:String

):Parcelable