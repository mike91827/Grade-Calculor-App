package com.example.newattempt.CourseSectionList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gradecalculatorrevision.CourseRoom.Course
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.newattempt.CourseList.CourseListFragmentDirections
import com.example.newattempt.R
import kotlinx.android.synthetic.main.coure_row.view.*
import kotlinx.android.synthetic.main.fragment_course_section_list.view.*

class CourseSectionListAdapter : RecyclerView.Adapter<CourseSectionListAdapter.MyViewHolder>() {

    //our list of course sections
    private var courseSectionList= emptyList<CourseSection>()


    //represents all the percent gained in a course section
    companion object{
        var percent:Int=0
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    //sets the row layout for the adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.course_section_row, parent, false)


        )

    }

    override fun getItemCount(): Int {
        return courseSectionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem=courseSectionList[position]
        //displays the course section with the amount of items it has
        holder.itemView.title.text=currentItem.title.toString()+" x"+currentItem.amount.toString()
        holder.itemView.current.text=currentItem.accountedPercent.toString()



        //when a course section is clicked, give the user an option to delete it
        holder.itemView.rowLayout.setOnClickListener{

            val action =

                CourseSectionListFragmentDirections.actionCourseSectionListFragmentToDeleteCourseSection(
                    courseSectionList[position]

                )
            holder.itemView.findNavController().navigate(action)
        }


    }


    fun getPercent():Int{
        return percent
    }



    //this displays all the course sections that corressponds to the Course with the title passed in the parameters
    fun setData(courseSection: List<CourseSection>,title:String){
        percent=0
        val temp:MutableList<CourseSection> = mutableListOf()

        for (i in 0..courseSection.size-1) {

            val cs: CourseSection = courseSection.get(i)

              if (cs.courseTitle.equals(title)) {
                percent = percent + cs.accountedPercent
                temp.add(cs)
              }
        }
        this.courseSectionList=temp
        notifyDataSetChanged()
    }
}