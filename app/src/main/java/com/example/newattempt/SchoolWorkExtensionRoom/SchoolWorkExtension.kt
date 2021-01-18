package com.example.newattempt.SchoolWorkExtensionRoom

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


//represents our school_work_extension/
//What this is is basically an extension to our school work
//School work can be anything like assignments, tests, and their is always a certain amount of those things.
//So if we have 6 assignments then the corressponding school work extensions will be six rows of assignment
//if we have 2 tests the school work extensions with be 2 rows of tests

@Parcelize
@Entity
    (tableName = "school_work_extensions_table")
data class SchoolWorkExtension (
    @PrimaryKey(autoGenerate = false)
    val title:String,
    //represents if we have recieved any marks
    val markRecieved:Boolean,
    //how much this works is part of your grade
    val percentTotal:Double,
    //the grade you recieved
    val percentEarned:Double,
    //represents if the user gave their mark as a fraction
    val fractionRecieved: Boolean,
    //the numberator of their mark
    val fractionNum:Double,
    //denominator of their mark
    val fractionDen:Double,
    //their corressponding school work
    val SchoolWorkTitle:String


):Parcelable