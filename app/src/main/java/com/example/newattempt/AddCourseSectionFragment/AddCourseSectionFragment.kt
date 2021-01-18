package com.example.newattempt.AddCourseSectionFragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSection
import com.example.gradecalculatorrevision.CourseSectionRoom.CourseSectionViewModel
import com.example.newattempt.R
import com.example.newattempt.SchoolWorkRoom.SchoolWorkViewModel
import kotlinx.android.synthetic.main.fragment_add_course.view.*
import kotlinx.android.synthetic.main.fragment_add_course_section.*

class AddCourseSectionFragment : Fragment() {
    //gets our arguments
    private val args by navArgs<AddCourseSectionFragmentArgs>()


    //how we access our course Section's database
    private lateinit var courseSectionViewModel: CourseSectionViewModel
    //how we access our school work's database
    private lateinit var schoolWorkViewModel: SchoolWorkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_add_course_section, container, false)

        courseSectionViewModel = ViewModelProvider(this).get(CourseSectionViewModel::class.java)
        schoolWorkViewModel = ViewModelProvider(this).get(SchoolWorkViewModel::class.java)

        //Adds course sections to the database
        view.Add.setOnClickListener {
            val success =insertDataToDatabase()
            if(success) {
                val action =

                        AddCourseSectionFragmentDirections.actionAddCourseSectionFragmentToCourseSectionListFragment(
                                //returns the course title
                                args.title,
                                //no permission to delete a course section
                                "no",
                                null
                        )
                findNavController().navigate(action)
            }
            // addSections()
        }
        return view
    }


    //inserts course section to the database
    //returns a boolean that represents if the insertion was successful
    private fun insertDataToDatabase():Boolean{
        //checks if the fields inputted are ok
        if (validInput(titlename.text.toString(),percentWorth.text.toString(),amount.text.toString())) {
            val title = titlename.text.toString()+ " for "+args.title
            val percent:Int = percentWorth.text.toString().toInt()
            val amount:Int = amount.text.toString().toInt()
            val cs = CourseSection(title,amount,percent,args.title)
            courseSectionViewModel.addCourseSection(cs)

            Toast.makeText(requireContext(), "INSERTED " + title, Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    //makes sure all the input fields are correct
    private fun validInput(title:String,percent:String,amount:String):Boolean{
        if(TextUtils.isEmpty(title)||TextUtils.isEmpty(percent)||TextUtils.isEmpty(amount)){
            Toast.makeText(requireContext(),"A field is empty",Toast.LENGTH_LONG).show()
            return false
        } else if (percent.toInt()>100||percent.toInt()<=0){
            Toast.makeText(requireContext(),"Percent cannot be over 100 or negative/zero",Toast.LENGTH_LONG).show()
            return false
        } else if(amount.toInt()<=0){
            Toast.makeText(requireContext(),"Amount cannot be negative or equal to zero",Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}