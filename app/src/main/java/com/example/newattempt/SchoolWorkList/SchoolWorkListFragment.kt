package com.example.newattempt.SchoolWorkList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradecalculatorrevision.CourseRoom.CourseViewModel
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.newattempt.CourseSectionList.CourseSectionListAdapter
import com.example.newattempt.CourseSectionList.CourseSectionListFragmentArgs
import com.example.newattempt.CourseSectionList.CourseSectionListFragmentDirections
import com.example.newattempt.R
import com.example.newattempt.SchoolWorkRoom.SchoolWork
import com.example.newattempt.SchoolWorkRoom.SchoolWorkViewModel
import kotlinx.android.synthetic.main.fragment_course_section_list.view.*
import kotlinx.android.synthetic.main.fragment_course_section_list.view.recyclerView
import kotlinx.android.synthetic.main.fragment_school_work_list.*
import kotlinx.android.synthetic.main.fragment_school_work_list.view.*


class SchoolWorkListFragment : Fragment() {
    private val args by navArgs<SchoolWorkListFragmentArgs>()

    //our databases
    private lateinit var schoolWorkViewModel: SchoolWorkViewModel
    private lateinit var courseViewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_school_work_list, container, false)

        //our adapter
        val adapter=
            SchoolWorkListAdapter()
        val recyclerView=view.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        schoolWorkViewModel= ViewModelProvider(this).get(SchoolWorkViewModel::class.java)
        courseViewModel= ViewModelProvider(this).get(CourseViewModel::class.java)


        //set's the following course their new percentCurrent, and percentLeft
        schoolWorkViewModel.readAllData.observe(viewLifecycleOwner, Observer {schoolWork:List<SchoolWork> ->
            if(args.course!=null)
                adapter.setData(schoolWork, args.course!!.title)
            else {
                adapter.setData(schoolWork,args.courseTitle)
            }
            totalPercent.setText("Total Earned Percent: "+adapter.getPercentEarned().toString()+"%")
            PercentLeft.setText("Percent Left To Earn: "+adapter.getPercentLeft().toString()+"%")
            courseViewModel.updatePercentCurrent(args.courseTitle,adapter.getPercentEarned().toString())
            courseViewModel.updatePercentLeft(args.courseTitle,adapter.getPercentLeft().toString())

        })



        return view
    }

    fun delete(schoolWork: SchoolWork){
        schoolWorkViewModel.deleteSchoolWork(schoolWork)
    }


}