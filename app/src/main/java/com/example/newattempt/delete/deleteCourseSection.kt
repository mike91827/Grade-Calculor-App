package com.example.newattempt.delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newattempt.R
//import com.example.newattempt.deleteCourseSectionArgs
//import com.example.newattempt.deleteCourseSectionDirections
import kotlinx.android.synthetic.main.fragment_delete_confirmation_.view.*

//fragment is responsible for confirming the user choice of deleting the course fragment
class deleteCourseSection : Fragment() {
    private val args by navArgs<deleteCourseSectionArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_delete_course_section, container, false)

        view.DeleteConfirmationtext.text="Are you sure you want to delete '"+args.deleteCourseSection.title+"'"

        //if yes os clicked, delete course section, and return to course section list
        view.YesDelete.setOnClickListener {
            val action =

                    deleteCourseSectionDirections.actionDeleteCourseSectionToCourseSectionListFragment(
                            args.deleteCourseSection.courseTitle,
                            "Yes",
                            args.deleteCourseSection
                    )
            findNavController().navigate(action)
        }

        //if no is clicked return to the course section list
        view.NoDelete.setOnClickListener {
            val action =

                    deleteCourseSectionDirections.actionDeleteCourseSectionToCourseSectionListFragment(
                            args.deleteCourseSection.courseTitle,
                            "No",
                            args.deleteCourseSection
                    )
            findNavController().navigate(action)

        }
        return view
    }


}