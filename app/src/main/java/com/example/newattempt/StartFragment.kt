package com.example.newattempt

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gradecalculatorrevision.CourseRoom.Course
import com.example.gradecalculatorrevision.CourseRoom.CourseViewModel
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionViewModel
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtensionViewModel
import com.example.newattempt.SchoolWorkRoom.SchoolWorkViewModel
import kotlinx.android.synthetic.main.fragment_start.view.*

//the start screen
class StartFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
    private lateinit var schoolWorkViewModel: SchoolWorkViewModel

    private lateinit var courseSectionViewModel: CourseSectionViewModel

    private lateinit var schoolWorkExtensionViewModel: SchoolWorkExtensionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_start, container, false)

        //brings the user to their course list
        view.ViewGrades.setOnClickListener {
            val action =

                StartFragmentDirections.actionStartFragmentToCourseListFragment(
                    "No",
                    Course("","","")
                )
            findNavController().navigate(action)

        }


//the mass delete button
        view.MassDelete.setOnClickListener {

            //the popup that confirms the user's choice
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("DELETE")
            alertDialogBuilder.setMessage("Are you sure you want to delete")

            //if yes, delete everything
            alertDialogBuilder.setPositiveButton("DELETE") {
                dialog, which ->
                courseViewModel = ViewModelProvider(this).get(CourseViewModel::class.java)
                courseSectionViewModel = ViewModelProvider(this).get(CourseSectionViewModel::class.java)
                schoolWorkViewModel = ViewModelProvider(this).get(SchoolWorkViewModel::class.java)
                schoolWorkExtensionViewModel = ViewModelProvider(this).get(SchoolWorkExtensionViewModel::class.java)
                courseViewModel.deleteAll()
                schoolWorkViewModel.deleteAll()
                courseSectionViewModel.deleteAll()
                schoolWorkExtensionViewModel.deleteAll()
                Toast.makeText(requireContext(),
                        "DELETED", Toast.LENGTH_SHORT).show()
            }

            //if no do not
            alertDialogBuilder.setNegativeButton("CANCEL"){
                dialog, which ->
                Toast.makeText(requireContext(),
                        "Did not delete", Toast.LENGTH_SHORT).show()
            }

            alertDialogBuilder.show()


        }

        return view
    }


}