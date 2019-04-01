package com.gmail.epamtraininghw

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.epamtraininghw.backend.entities.Student
import kotlinx.android.synthetic.main.student_item_view.view.*
import java.util.*

class StudentsAdapter : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    private var students = arrayListOf<Student>()
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item_view_frame, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student, listener)
    }

    fun addItems(result: List<Student>) {
        students.addAll(result)
        notifyDataSetChanged()
    }

    fun updateItems(items: List<Student>) {
        students.clear()
        students.addAll(items)
        notifyDataSetChanged()
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(students, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(students, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

    private fun deleteByIndex(i: Int) {
        students.removeAt(i)
        notifyItemRemoved(i)
    }

    fun onItemDismiss(adapterPosition: Int) {
        deleteByIndex(adapterPosition)
    }

    class StudentViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var id: Long = 0

        fun bind(student: Student, listener: OnItemClickListener) {
            id = student.id
            itemView.studentNameTextView.text = student.name
            itemView.hwTextView.text = student.hWCount.toString()
            itemView.setOnClickListener {
                listener.onItemClick(id)
            }
        }
    }

    fun setOnItemClickListener(itemListener: OnItemClickListener) {
        listener = itemListener
    }

    interface OnItemClickListener {
        fun onItemClick(id: Long)
    }
}