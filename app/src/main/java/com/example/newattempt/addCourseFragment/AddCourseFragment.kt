package com.example.gradecalculatorrevision.addCourseFragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gradecalculatorrevision.CourseRoom.Course
import com.example.gradecalculatorrevision.CourseRoom.CourseViewModel

import com.example.newattempt.R
import kotlinx.android.synthetic.main.fragment_add_course.*
import kotlinx.android.synthetic.main.fragment_add_course.view.*


class AddCourseFragment : Fragment() {

    //how we access our course's database
    private lateinit var courseViewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_add_course, container, false)

        courseViewModel = ViewModelProvider(this).get(CourseViewModel::class.java)


        //when clicked, add the typed in course to the database if it is a valid title. Then go to the course section list if succeeded
        view.Add.setOnClickListener {

            val id =saveCourse()


            if(id!=null) {
                val action =
                        AddCourseFragmentDirections.actionAddCourseFragmentToCourseSectionListFragment(
                                //title of currently added course
                                id,
                                //no permission to delete anything
                                "no",
                                null
                        )


                findNavController().navigate(action)
            }

        }

        return view
    }



    //saves the course to the database, if and only if, the title is valid
    private fun saveCourse():String?{

        val t = title.text.toString()
        if(inputCheck(t)) {
            val course = Course(t, "0%", "100%")
            courseViewModel.addCourse(course)
            Toast.makeText(requireContext(), t + " added", Toast.LENGTH_LONG).show()
            return t
        }
        Toast.makeText(requireContext(), "Invalid", Toast.LENGTH_LONG).show()
        return null

    }

    //checks if the string is not blank
    private fun inputCheck(title:String):Boolean{
        return !TextUtils.isEmpty(title)
    }

}