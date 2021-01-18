package com.example.newattempt.SchoolWorkList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.example.newattempt.R
import com.example.newattempt.SchoolWorkRoom.SchoolWork

import kotlinx.android.synthetic.main.school_work_row.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class SchoolWorkListAdapter : RecyclerView.Adapter<SchoolWorkListAdapter.MyViewHolder>() {


    //all the school work
    private var schoolWorkList= emptyList<SchoolWork>()

    companion object{
        //represents the amount earned in this school work
        private var percentEarned:Double=0.00
        //represents the amount left to earn in this school work
        private var percentLeft:Double=0.00
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.school_work_row, parent, false)


        )

    }

    override fun getItemCount(): Int {
        return schoolWorkList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem=schoolWorkList[position]
        holder.itemView.SchoolWorkTitle.text=currentItem.title.toString()+" x"+currentItem.amount.toString()
        holder.itemView.SchoolWorkPercentEarned.text= currentItem.percentEarned.toString()+"%"
        holder.itemView.SchoolWorkPercentLeft.text=currentItem.percentLeft.toString()+"%"

        //when a row is clicked take them to the corressponding school work extension list
      holder.itemView.rowLayout.setOnClickListener{

            val temp=schoolWorkList[position]

            val action =

                SchoolWorkListFragmentDirections.actionSchoolWorkListFragmentToSchoolWorkExtensionFragment(
                    temp,
                    temp.title,
                    temp.courseTitle
                )
            holder.itemView.findNavController().navigate(action)

        }




    }

    //only show the school work the corressponds to the passed course (title)
    fun setData(schoolWork: List<SchoolWork>,title:String){
        percentEarned=0.00
        percentLeft = 0.00
        val temp:MutableList<SchoolWork> = mutableListOf()
        for (i in 0..schoolWork.size-1) {

            val cs: SchoolWork = schoolWork.get(i)

           if (cs.courseTitle.equals(title)) {
                percentEarned= percentEarned+cs.percentEarned
                temp.add(cs)
                percentLeft= percentLeft+cs.percentLeft
            }
        }

        percentEarned = roundOffDecimal(percentEarned)
        percentLeft=roundOffDecimal(percentLeft)

        this.schoolWorkList=temp
        notifyDataSetChanged()
    }

    //round to nearest 2 decimals
    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

    fun getPercentLeft():Double{
        return percentLeft
    }

    fun getPercentEarned():Double{
        return percentEarned
    }
}