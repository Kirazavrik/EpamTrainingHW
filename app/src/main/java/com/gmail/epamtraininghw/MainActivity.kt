package com.gmail.epamtraininghw

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.gmail.epamtraininghw.backend.IWebService
import com.gmail.epamtraininghw.backend.StudentsWebService
import com.gmail.epamtraininghw.backend.entities.Student
import com.gmail.epamtraininghw.util.ICallback

class MainActivity : AppCompatActivity(), EditStudentDialog.EditStudentDialogListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentsAdapter: StudentsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var webService: IWebService<Student> = StudentsWebService()
    private lateinit var itemTouchHelper: ItemTouchHelper
    val editStudentDialog = EditStudentDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {
        viewManager = LinearLayoutManager(this)
        studentsAdapter = StudentsAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = viewManager
            adapter = studentsAdapter
        }

        val listener = object : StudentsAdapter.OnItemClickListener {
            override fun onItemClick(id: Long) {
                editStudentDialog.setId(id)
                startEditStudentDialog()
            }

        }

        studentsAdapter.setOnItemClickListener(listener)

        itemTouchHelper = ItemTouchHelper(ItemTouchCallback(studentsAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        loadItems()
    }

    private fun loadItems() {
        webService.getEntities(object : ICallback<List<Student>> {
            override fun onResult(result: List<Student>) {
                studentsAdapter.addItems(result)
            }
        })
    }

    private fun updateItems() {
        webService.getEntities(object : ICallback<List<Student>> {
            override fun onResult(result: List<Student>) {
                studentsAdapter.updateItems(result)
            }
        })
    }

    override fun onFinishEditDialog(id: Long, studentName: String, hw: String) {
        webService.editEntity(id, studentName, hw.toInt())
        updateItems()
    }

    private fun startEditStudentDialog() {
        val fragmentManager = supportFragmentManager
        editStudentDialog.show(fragmentManager, "edit_student_dialog")
    }
}
