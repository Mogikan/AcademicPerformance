package com.example.korney.academicperformance

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.widget.RelativeLayout
import com.example.korney.academicperformance.model.BusinessLogic
import com.example.korney.academicperformance.model.Student
import com.example.korney.academicperformance.model.StudyGroup

import kotlinx.android.synthetic.main.activity_students.*
import kotlinx.android.synthetic.main.student_edit.view.*

class StudentsActivity : AppCompatActivity(),StudentsActivityFragment.OnListFragmentInteractionListener {
    var groupId:String? = null
    val group:StudyGroup?
        get() = groupId?.let { BusinessLogic.getGroupById(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)
        groupId = intent.getStringExtra(AcademicPerformanceActivity.ARG_GROUP_ID)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.student_Fragment,StudentsActivityFragment.newInstance(1,groupId!!))
            commit()
        }
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            val view = layoutInflater.inflate(R.layout.student_edit,null)
            AlertDialog.Builder(this)
                    .setView(view)
                    .setPositiveButton("OK", {_,_->
                        BusinessLogic.addStudent(group!!,
                                view.name_textbox.text.toString(),
                                view.surname_textbox.text.toString())
                    })
                    .setNegativeButton("Cancel", {_,_->

                    })
                    .show()


        }
    }

    override fun onListFragmentInteraction(student: Student) {

    }

}
