package com.example.korney.academicperformance.model

/**
 * Created by Korney on 11/7/2017.
 */
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class StudyGroup:RealmObject(){
    @PrimaryKey
    var id:String? = null
    var name: String? = null
    var students: RealmList<Student> = RealmList()
}