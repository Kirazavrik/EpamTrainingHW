package com.gmail.epamtraininghw.backend

import com.gmail.epamtraininghw.backend.entities.Student
import com.gmail.epamtraininghw.util.ICallback

interface IWebService<T> {

    fun getEntities(callback: ICallback<List<T>>)

    fun getRangeEntities(startRange: Int,
                         endRange: Int,
                         callback: ICallback<List<T>>)

    fun addEntity(student: Student)

    fun editEntity(id: Long, name: String, hwCount: Int)

    fun removeEntity(id: Long)
}