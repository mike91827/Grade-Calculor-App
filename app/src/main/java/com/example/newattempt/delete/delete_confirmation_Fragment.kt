package com.example.newattempt.delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newattempt.R
//import com.example.newattempt.delete_confirmation_FragmentArgs
//import com.example.newattempt.delete_confirmation_FragmentDirections
import kotlinx.android.synthetic.main.fragment_delete_confirmation_.view.*



//this is the fragment responsible for deleting a course
class delete_confirmation_Fragment : Fragment() {
    private val args by navArgs<delete_confirmation_FragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delete_confirmation_, container, false)

        view.DeleteConfirmationtext.text="Are you sure you want to delete '"+args.deleteCourse.title+"'"

        //if user clicks yes, delete course, and return to course list
        view.YesDelete.setOnClickListener {
            val action =

                    delete_confirmation_FragmentDirections.actionDeleteConfirmationFragmentToCourseListFragment(
                            "Yes",
                            args.deleteCourse
                    )
            findNavController().navigate(action)
        }

        //if user clicks no, return to course list
        view.NoDelete.setOnClickListener {
            val action =

                    delete_confirmation_FragmentDirections.actionDeleteConfirmationFragmentToCourseListFragment(
                            "No",
                            args.deleteCourse
                    )
            findNavController().navigate(action)

        }
        return view
    }



}