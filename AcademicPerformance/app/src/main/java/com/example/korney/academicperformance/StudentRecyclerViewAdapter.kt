package com.example.korney.academicperformance

import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import com.example.korney.academicperformance.StudentsActivityFragment.OnListFragmentInteractionListener
import com.example.korney.academicperformance.model.BusinessLogic
import com.example.korney.academicperformance.model.Student
import com.example.korney.academicperformance.model.StudyGroup
import kotlinx.android.synthetic.main.fragment_student.view.*
import kotlinx.android.synthetic.main.student_edit.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class StudentRecyclerViewAdapter(private val mListener: OnListFragmentInteractionListener?, private var mGroup: StudyGroup) : RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
                .inflate(R.layout.fragment_student, parent, false)
        val holder = ViewHolder(view)
        view.delete_student.setOnClickListener {
            BusinessLogic.deleteStudent(holder.mItem!!)
            updateItems()
        }
        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
            val view = layoutInflater.inflate(R.layout.student_edit,null)
            view.name_textbox.setText(holder.mItem!!.name)
            view.surname_textbox.setText(holder.mItem!!.surname)
            AlertDialog.Builder(parent.context)
                    .setView(view)
                    .setPositiveButton("OK", {_,_->
                        val name = view.name_textbox.text.toString()
                        val surname = view.surname_textbox.text.toString()
                        BusinessLogic.updateStudent(holder.mItem!!,name,surname)
                        updateItems()
                    })
                    .setNegativeButton("Cancel", {_,_->

                    })
                    .show()
        }
        return holder
    }
    val mValues:List<Student>
        get() = mGroup.students

    private fun updateItems() {
        mGroup = BusinessLogic.getGroupById(mGroup.id!!)!!
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        //holder.mIdView.text = mValues[position].id
        holder.mContentView.text = mValues[position].fullname
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //val mIdView: TextView
        val mContentView: TextView
        var mItem: Student? = null

        init {
            //mIdView = mView.findViewById<View>(R.id.id) as TextView
            mContentView = mView.findViewById<View>(R.id.student_fullName) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
