package com.example.catatan

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class ModelData(): RealmObject() {
    private var Id : Int? = null
    private var Title : String = ""
    private var Descripsions : String? = null

    fun setId(Id : Int){
        this.Id = Id
    }
    fun getId(): Int?{
        return Id
    }
    fun setTitle(Title : String){
        this.Title = Title
    }
    fun getTitle(): String?{
        return Title
    }
    fun setDesc(Desc : String){
        this.Descripsions = Desc
    }
    fun getDesc(): String?{
        return Descripsions
    }
}