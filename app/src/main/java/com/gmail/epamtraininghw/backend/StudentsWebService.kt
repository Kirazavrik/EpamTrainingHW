package com.gmail.epamtraininghw.backend

import android.os.Handler
import android.os.Looper
import com.gmail.epamtraininghw.backend.entities.Student
import com.gmail.epamtraininghw.util.ICallback
import java.util.*

class StudentsWebService : IWebService<Student> {

    private var students = arrayListOf<Student>()
    private var random: Random = Random()
    private var handler: Handler = Handler(Looper.getMainLooper())

    init {
        for (i in 0..20) {
            val student = Student(i.toLong(), "Student$i", 1 + random.nextInt(5))
            students.add(student)
        }
    }

    override fun getEntities(callback: ICallback<List<Student>>) {
        handler.post {
            callback.onResult(students)
        }
    }

    override fun getRangeEntities(startRange: Int,
                                  endRange: Int,
                                  callback: ICallback<List<Student>>) {
        handler.post {
            callback.onResult(students.subList(startRange, endRange))
        }
    }

    override fun addEntity(student: Student) {
        handler.post {
            students.add(student)
        }
    }

    override fun editEntity(id: Long, name: String, hwCount: Int) {
        for (student in students) {
            if (student.id == id) {
                student.name = name
                student.hWCount = hwCount
                break
            }
        }
    }

    override fun removeEntity(id: Long) {
        for (student in students) {
            if (student.id == id) {
                students.remove(student)
                break
            }
        }
    }
}