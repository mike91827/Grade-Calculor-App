package com.example.newattempt.CourseSectionList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradecalculatorrevision.CourseRoom.Course
import com.example.gradecalculatorrevision.CourseRoom.CourseViewModel
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionViewModel
import com.example.newattempt.R
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtension
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtensionViewModel
import com.example.newattempt.SchoolWorkRoom.SchoolWork
import com.example.newattempt.SchoolWorkRoom.SchoolWorkViewModel
import kotlinx.android.synthetic.main.fragment_course_section_list.view.*


class CourseSectionListFragment : Fragment() {
    //gets the parameters
    private val args by navArgs<CourseSectionListFragmentArgs>()

    //all our databases
    private lateinit var courseViewModel: CourseViewModel
    private lateinit var schoolWorkViewModel: SchoolWorkViewModel
    private lateinit var courseSectionViewModel: CourseSectionViewModel
    private lateinit var schoolWorkExtensionViewModel: SchoolWorkExtensionViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_course_section_list, container, false)

        //setup our adapter
        val adapter=
            CourseSectionListAdapter()
        val recyclerView=view.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())


        //sets up our database
        courseViewModel= ViewModelProvider(this).get(CourseViewModel::class.java)
        courseSectionViewModel= ViewModelProvider(this).get(CourseSectionViewModel::class.java)
        schoolWorkViewModel= ViewModelProvider(this).get(SchoolWorkViewModel::class.java)
        schoolWorkExtensionViewModel= ViewModelProvider(this).get(SchoolWorkExtensionViewModel::class.java)


        //if the fragment's paramters give us permission to delete a course, we delete the course passed in the parameters
        if(args.deleteCourseSectionPermission=="Yes"){
            deleteCourseSection(args.deleteCourseSection!!)
        }


       //sets up the recycler view with the adapter
       courseSectionViewModel.readAllData.observe(viewLifecycleOwner, Observer {courseSection:List<CourseSection> ->
            adapter.setData(courseSection,args.title)
        })



        //this is for the confirm button. Make's sure all the user's class add up to 100%. If it does we return to the course list
        view.Confirm.setOnClickListener {
            if(adapter.getPercent()==100) {

                val action =
                    CourseSectionListFragmentDirections.actionCourseSectionListFragmentToCourseListFragment(
                        "No",
                        Course("", "", "")
                    )
                Toast.makeText(requireContext(), "Now going back to main list",Toast.LENGTH_LONG).show()
                addSchoolWork(args.title)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "All your courses need to add up to 100%. Right now only "+adapter.getPercent()+"% is filled", Toast.LENGTH_LONG).show()
            }
        }


        //when clicked takes you to the fragment where you can add a course extension
        view.AddCourseExtension.setOnClickListener {
            val action =

                CourseSectionListFragmentDirections.actionCourseSectionListFragmentToAddCourseSectionFragment(

                    args.title
                )
            findNavController().navigate(action)
        }

        return view
    }


    //adds a school Work item to the database, makes sure there is a corressponding class.
    //Also adds all the school work extensions to that school work automatically
    fun addSchoolWork(courseTitle:String){

        courseSectionViewModel.readAllData.observe(viewLifecycleOwner, Observer {courseSection:List<CourseSection> ->
            for (i in 0..courseSection.size-1){
                var temp=courseSection.get(i)
                if(temp.courseTitle.equals(courseTitle)){

                    schoolWorkViewModel.addSchoolWork(SchoolWork(temp.title,temp.amount,temp.accountedPercent.toDouble(),0.00,temp.accountedPercent.toDouble(),temp.courseTitle))
                    addSchoolWorkExtension(temp.title,temp.amount,temp.accountedPercent.toDouble())
                }
            }
        })

    }


    //adds all the necessary school extensions
    fun addSchoolWorkExtension(title:String,amount:Int,percent:Double){
        for (i in 1..amount){
            schoolWorkExtensionViewModel.addSchoolWorkExtension(SchoolWorkExtension(title+" #"+i.toString(),false,percent,0.00,false,0.00,0.00,title))
        }

    }


    //delete course section
    fun deleteCourseSection(courseSection: CourseSection){
        courseSectionViewModel.deleteCourseSection(courseSection)

    }

}