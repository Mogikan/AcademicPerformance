package com.example.korney.academicperformance.model

import android.content.Context
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.Sort
import java.util.*

/**
 * Created by Korney on 11/8/2017.
 */
object BusinessLogic{
    fun init(context: Context) {
        Realm.init(context)
    }

   fun getGroups(): List<StudyGroup> {

        var realm = Realm.getDefaultInstance()
        return realm.where(StudyGroup::class.java).findAll().sort("name")
   }

    fun getGroupById(id:String):StudyGroup?{
        var realm = Realm.getDefaultInstance()
        return realm.where(StudyGroup::class.java).equalTo("id",id).findFirst()
    }

    fun getStudents(group:StudyGroup):List<Student>{
        return group.students
    }

    fun updateStudent(student:Student,name:String,surname:String){
        inTransaction {
            realm->
            student.name = name
            student.surname = surname
            realm.insertOrUpdate(student)
        }
    }

    fun addStudent(group: StudyGroup,name:String,surname:String):Unit{
        var realm = Realm.getDefaultInstance()
        inTransaction {
            val student = realm.createObject(Student::class.java, UUID.randomUUID().toString())
            student.name = name
            student.surname = surname
            group.students.add(student)
        }
    }

//    fun inTransaction(action:()->Unit){
//        var realm = Realm.getDefaultInstance()
//        realm.executeTransaction {
//            action()
//        }
//    }

    fun inTransaction(action:(realm:Realm)->Unit){
        var realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            action(realm)
        }
    }

    fun addGroup(groupName:String)
    {
        var realm = Realm.getDefaultInstance()
        inTransaction{
            val group = realm.createObject(StudyGroup::class.java,UUID.randomUUID().toString())
            group.name = groupName
        }

    }

    fun deleteGroup(group:StudyGroup){
       inTransaction {
           group.deleteFromRealm()
       }
    }

    fun deleteStudent(student:Student){
        inTransaction {
            student.deleteFromRealm()
        }
    }
}