package com.example.newattempt.CourseList

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradecalculatorrevision.CourseRoom.Course
import com.example.gradecalculatorrevision.CourseRoom.CourseViewModel
import com.example.newattempt.R
import com.example.newattempt.SchoolWorkRoom.SchoolWorkViewModel
import kotlinx.android.synthetic.main.fragment_course_list.view.*

class CourseListFragment : Fragment(){
    //gets the parameters
    private val args by navArgs<CourseListFragmentArgs>()
    //our databases
    private lateinit var courseViewModel: CourseViewModel
    private lateinit var schoolWorkViewModel: SchoolWorkViewModel

    //represents whether fragment is in delete mode. If it is in delete mode whatever course we click on will be deleted
    private var delete:Boolean=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_course_list, container, false)

        //our adapter for the recycler view
        val adapter=
            CourseListAdapter()

        //sets the layout for the recycler view
        val recyclerView=view.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())


        courseViewModel= ViewModelProvider(this).get(CourseViewModel::class.java)
        schoolWorkViewModel=ViewModelProvider(this).get(SchoolWorkViewModel::class.java)


        //if the fragment was passed the parameter Yes for PermissionTODeleteCourse, will delete the passed Course
        if(args.PermissionToDeleteCourse=="Yes"){
            deleteCourse(args.Course)
        }


        //sets the data for the recycler view
        courseViewModel.readAllData.observe(viewLifecycleOwner, Observer {course ->
            adapter.setData(course)
        })


        //changes the color of the delete button and if this fragment is in delete mode
        view.DeleteButton.setOnClickListener {
            delete = delete==false
            adapter.setDelete(delete)
            if(delete==false){
                view.DeleteButton.setBackgroundColor(Color.BLUE)
            } else {
                view.DeleteButton.setBackgroundColor(Color.RED)
            }
        }

        //when add button is click go to add course fragment
        view.AddCourseExtension.setOnClickListener {
            findNavController().navigate(R.id.action_courseListFragment_to_addCourseFragment)
        }
        return view
    }

//deletes a course
    fun deleteCourse(course: Course){
        courseViewModel.deleteCourse(course)

    }
}