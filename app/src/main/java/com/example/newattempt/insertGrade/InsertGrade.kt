package com.example.newattempt.insertGrade

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
//import com.example.newattempt.InsertGradeArgs
//import com.example.newattempt.InsertGradeDirections
import com.example.newattempt.R
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtension
import com.example.newattempt.SchoolWorkExtensionRoom.SchoolWorkExtensionViewModel
import com.example.newattempt.SchoolWorkRoom.SchoolWorkViewModel
import kotlinx.android.synthetic.main.fragment_insert_grade.*
import kotlinx.android.synthetic.main.fragment_insert_grade.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

//this is where the user enter's their grade they got on a school work extension
class InsertGrade : Fragment() {
    private val args by navArgs<InsertGradeArgs>()
    //our databases
    private lateinit var schoolWorkViewModel: SchoolWorkViewModel
    private lateinit var schoolWorkExtensionViewModel: SchoolWorkExtensionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view= inflater.inflate(R.layout.fragment_insert_grade, container, false)


        schoolWorkViewModel= ViewModelProvider(this).get(SchoolWorkViewModel::class.java)
        schoolWorkExtensionViewModel= ViewModelProvider(this).get(SchoolWorkExtensionViewModel::class.java)

        //When the submit button is clicked,3 things can happen.
        //the user can fill out their mark as a percent OR fraction
        //if the user submits their mark as a fraction, check if it's a valid fraction then add the mark to the database
        //if it is a percent do something similar
        //if neither is filled, say they must fill either one out
        view.Submit.setOnClickListener {
            val num = numerator.text.toString()
            val den = denominator.text.toString()
            val percent = Percent.text.toString()
            val action:NavDirections
            if(!TextUtils.isEmpty(num)&&!TextUtils.isEmpty(den)){
                updateTable(
                        true,
                    num.toDouble()/den.toDouble()*100
                    ,true,
                    num.toDouble(),den.toDouble())
                action = InsertGradeDirections.actionInsertGradeToSchoolWorkExtensionFragment(
                        args.SchoolWork,
                        args.SchoolWorkExstention.SchoolWorkTitle,
                        args.CourseTitle
                )
                findNavController().navigate(action)
            } else if (!TextUtils.isEmpty(percent)) {
                updateTable(true,percent.toDouble(),false,0.0,0.0)
                action = InsertGradeDirections.actionInsertGradeToSchoolWorkExtensionFragment(
                        args.SchoolWork,
                        args.SchoolWorkExstention.SchoolWorkTitle,
                        args.CourseTitle
                )
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Must fill In fraction or percent", Toast.LENGTH_LONG).show()
            }
        }


        //if reset button is clicked, then erase all the data the grade had and act like no grade was inputted yet
        view.reset.setOnClickListener {
            updateTable(
                    false,
                    0.00,
                    false,
                    0.00,
                    0.00
            )
            val action = InsertGradeDirections.actionInsertGradeToSchoolWorkExtensionFragment(
                    args.SchoolWork,
                    args.SchoolWorkExstention.SchoolWorkTitle,
                    args.CourseTitle
            )
            findNavController().navigate(action)
        }
        return view
    }



    private fun updateTable(marksRecieved:Boolean,percentGained:Double,fractionRecieved:Boolean,num:Double,den:Double){

        //schoolWorkViewModel.getSchoolWork(args.schoolWorkExstenstion.SchoolWorkTitle)
        //val temp:SchoolWork=schoolWorkViewModel.sc
      //  Log.d("myTag",temp.title)
        //schoolWorkViewModel.updatePercentEarned(args.schoolWorkExstenstion.SchoolWorkTitle,)

        schoolWorkExtensionViewModel.updateSchoolWorkExtension(SchoolWorkExtension(
            args.SchoolWorkExstention.title,
            marksRecieved,
            args.SchoolWorkExstention.percentTotal,
            roundOffDecimal(percentGained),
            fractionRecieved,
            roundOffDecimal(num),
            roundOffDecimal(den),
            args.SchoolWorkExstention.SchoolWorkTitle
            ))


    }


    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

}