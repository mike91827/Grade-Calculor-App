package com.example.newattempt.SchoolWorkExtensionList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.newattempt.CourseList.CourseListFragmentDirections
import com.example.newattempt.CourseSectionList.CourseSectionListAdapter
import com.example.newattempt.CourseSectionList.CourseSectionListFragmentDirections
import com.example.newattempt.R
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtension
import com.example.newattempt.SchoolWorkRoom.SchoolWork
import kotlinx.android.synthetic.main.coure_row.view.*
import kotlinx.android.synthetic.main.coure_row.view.rowLayout
import kotlinx.android.synthetic.main.coure_row.view.title
import kotlinx.android.synthetic.main.school_work_extension_row.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class SchoolWorkExtensionAdapter(course:String,schoolWork:SchoolWork): RecyclerView.Adapter<SchoolWorkExtensionAdapter.MyViewHolder>() {

    private var schoolWorkExtensionList= emptyList<SchoolWorkExtension>()
    //the course the corressponds to this school work extension
    private val cor=course
    //the school work that corressponds to this school work extension
    private val schoolWork=schoolWork


    companion object{
        //total percent earned/percent left by all the school work extensions corressponding to their school work
        var earned:Double=0.00
       var percentLeft:Double=0.00
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolWorkExtensionAdapter.MyViewHolder {
        return MyViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.school_work_extension_row, parent, false)


        )

    }

    override fun getItemCount(): Int {
        return schoolWorkExtensionList.size
    }

    override fun onBindViewHolder(holder: SchoolWorkExtensionAdapter.MyViewHolder, position: Int) {

        val currentItem=schoolWorkExtensionList[position]
        holder.itemView.title.text=currentItem.title.toString()

        //if the corressponding school work extensions' marksRecieved parameter is set to true, display their marks
        //otherwise just say there is no grade
        if(currentItem.markRecieved) {
            holder.itemView.percent.text = currentItem.percentEarned.toString() + "%"
            if(currentItem.fractionRecieved) {
                holder.itemView.fraction.text =
                        currentItem.fractionNum.toString() + "/" + currentItem.fractionDen.toString()
            }
        } else {
            holder.itemView.percent.text = "No Grade"

        }



        //when the user clicks an item, allow them to insert a grade
        holder.itemView.rowLayout.setOnClickListener{
            val temp=schoolWorkExtensionList[position]
            val action =

                SchoolWorkExtensionFragmentDirections.actionSchoolWorkExtensionFragmentToInsertGrade(
                    temp,
                    cor,
                        schoolWork

                )
            holder.itemView.findNavController().navigate(action)

        }


    }

    fun getEarned():Double{
        return earned
    }
    fun getPercentLeft():Double{
        return percentLeft
    }


    //sets data for the adapter row layout while also setting the earned and percentLeft global parameters
    fun setData(schoolWorkExtension: List<SchoolWorkExtension>,title:String){

        earned=0.00

        //multiplier determines how much the grade matters
        var multipler:Double= schoolWork.percentTotal/schoolWork.amount
        var percentAccountFor=schoolWork.percentTotal
        val temp:MutableList<SchoolWorkExtension> = mutableListOf()

        for (i in 0..schoolWorkExtension.size-1) {
            val cs: SchoolWorkExtension = schoolWorkExtension.get(i)

           if (cs.SchoolWorkTitle.equals(title)) {
               if(cs.markRecieved) {
                   earned = earned + (cs.percentEarned / 100) * multipler
                   percentAccountFor=percentAccountFor-multipler
               }

                temp.add(cs)

            }
        }

        percentLeft=roundOffDecimal(percentAccountFor)
        earned=roundOffDecimal(earned)

        this.schoolWorkExtensionList=temp
        notifyDataSetChanged()

    }

    //rounds to the nearest 2 decimals
    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

}