package com.example.korney.academicperformance
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.example.korney.academicperformance.model.BusinessLogic
import com.example.korney.academicperformance.model.StudyGroup
import kotlinx.android.synthetic.main.activity_academic_performance.*

class AcademicPerformanceActivity : AppCompatActivity(),GroupFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: StudyGroup) {
        val groupId = item.id
        val studentsIntent = Intent(this,StudentsActivity::class.java)
        studentsIntent.putExtra(ARG_GROUP_ID,groupId)
        startActivity(studentsIntent)
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    var StudyGroupFragment: GroupFragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                //message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_subjects -> {
                //message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_groups -> {
                //message.setText("Groups")
                supportFragmentManager.inTransaction {
                    if (StudyGroupFragment==null){
                        StudyGroupFragment = GroupFragment()
                    }
                    replace(R.id.main_fragment,StudyGroupFragment!!)
                }
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_types->{
                //message.setText("Types")

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

//    val mMessageReceiver: BroadcastReceiver = object:BroadcastReceiver() {
//        override fun onReceive(context:Context?, intent:Intent?) {
//            intent?.let {
//                intention->
//                {
//                    val groupId = intention.getStringExtra(ARG_GROUP_ID)
//                    val studentsIntent:Intent = Intent(StudentsActivity::javaClass.name)
//                    studentsIntent.putExtra(ARG_GROUP_ID,groupId)
//                    startActivity(studentsIntent)
//                }
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
//                IntentFilter(OPEN_GROUP_STUDENTS));

        setContentView(R.layout.activity_academic_performance)
        BusinessLogic.init(applicationContext)
        supportFragmentManager.inTransaction {
            if (StudyGroupFragment==null){
                StudyGroupFragment = GroupFragment()
            }
            add(R.id.main_fragment,StudyGroupFragment!!)
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    companion object {
        val OPEN_GROUP_STUDENTS = "open-group-students"
        val ARG_GROUP_ID = "group-id"
    }
}
