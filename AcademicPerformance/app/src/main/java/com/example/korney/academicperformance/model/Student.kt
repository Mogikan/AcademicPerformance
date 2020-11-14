package com.example.korney.academicperformance.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Korney on 11/7/2017.
 */
open class Student:RealmObject(){
    @PrimaryKey
    var id: String? = null
    var name: String? = null
    var surname: String? = null
    var group:StudyGroup? = null
    val fullname
        get() = String.format("%s %s",name,surname)
}