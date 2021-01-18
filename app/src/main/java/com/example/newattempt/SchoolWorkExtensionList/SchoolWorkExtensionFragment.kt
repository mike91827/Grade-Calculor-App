package com.example.newattempt.SchoolWorkExtensionList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newattempt.R
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtension
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtensionViewModel
import com.example.newattempt.SchoolWorkRoom.SchoolWorkViewModel
import kotlinx.android.synthetic.main.fragment_course_section_list.view.recyclerView
import kotlinx.android.synthetic.main.fragment_school_work_extension.view.*


class SchoolWorkExtensionFragment : Fragment() {

    private val args by navArgs<SchoolWorkExtensionFragmentArgs>()

    //databases
    private lateinit var schoolWorkViewModel: SchoolWorkViewModel
    private lateinit var schoolWorkExtensionViewModel: SchoolWorkExtensionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_school_work_extension, container, false)

        //our adapter
        val adapter=
            SchoolWorkExtensionAdapter(args.courseTitle,args.SchoolWork)
        val recyclerView=view.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        schoolWorkViewModel= ViewModelProvider(this).get(SchoolWorkViewModel::class.java)
        schoolWorkExtensionViewModel= ViewModelProvider(this).get(SchoolWorkExtensionViewModel::class.java)


        schoolWorkExtensionViewModel.readAllData.observe(viewLifecycleOwner, Observer {schoolWorkExtension:List<SchoolWorkExtension> ->

            adapter.setData(schoolWorkExtension, args.SchoolWorkTitle)
            //updates the percent earned by adding all the percent earned by the corressponing school work extension
            schoolWorkViewModel.updatePercentEarned(args.SchoolWorkTitle,adapter.getEarned())
            //updates the percent left by adding all the percent left by the corressponing school work extension
            schoolWorkViewModel.updatePercentLeft(args.SchoolWorkTitle,adapter.getPercentLeft())
        })




        //returns to the school work list
        view.GoBack.setOnClickListener {
            val action =
                SchoolWorkExtensionFragmentDirections.actionSchoolWorkExtensionFragmentToSchoolWorkListFragment(
                    null,
                    args.courseTitle
                )
            findNavController().navigate(action)
        }
        return view
    }


}