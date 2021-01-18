package com.example.newattempt.CourseList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.gradecalculatorrevision.CourseRoom.Course
import com.example.gradecalculatorrevision.CourseRoom.CourseViewModel
import com.example.newattempt.R

import kotlinx.android.synthetic.main.coure_row.view.*

class CourseListAdapter: RecyclerView.Adapter<CourseListAdapter.MyViewHolder>()  {

    //the list of courses
    private var courseList= emptyList<Course>()

    //checks if fragment is in delete mode
    private var delete:Boolean=false

    //needed just for the adapter
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //for the layout
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.coure_row,
                parent,
                false
            )
        )

    }


    //returns the amount of courses
    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //set text of items on fragment
        val currentItem=courseList[position]
        holder.itemView.title.text=currentItem.title.toString()
        holder.itemView.current.text=currentItem.percentCurrent.toString()
        holder.itemView.leftpercent.text=currentItem.percentLeft.toString()


        //whatever course we click on (displayed in recycler view), go to delete course if this fragment is in delete mode, else go to school work list
        holder.itemView.rowLayout.setOnClickListener{
            if(delete) {
                val action =

                    CourseListFragmentDirections.actionCourseListFragmentToDeleteConfirmationFragment(
                        courseList[position]

                    )
                holder.itemView.findNavController().navigate(action)
            } else {
                val action =

                    CourseListFragmentDirections.actionCourseListFragmentToSchoolWorkListFragment(
                        courseList[position],
                        courseList[position].title

                    )
                holder.itemView.findNavController().navigate(action)
            }
        }
    }


    fun setDelete(temp:Boolean){
        delete=temp
    }


    //this is for setting the recycler view
    fun setData(course: List<Course>){
        this.courseList=course
        notifyDataSetChanged()
    }
}