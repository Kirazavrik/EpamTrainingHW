package com.gmail.epamtraininghw

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.edit_student_dialog.*

class EditStudentDialog : DialogFragment() {

    private var listener: EditStudentDialogListener? = null
    private var studentId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_student_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener = activity as EditStudentDialogListener


        doneButton.setOnClickListener {
            val name: String = nameEditText.text.toString()
            val hw: String = hwEditText.text.toString()
            listener!!.onFinishEditDialog(studentId, name, hw)
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    fun setId(id: Long) {
        studentId = id
    }

    interface EditStudentDialogListener {
        fun onFinishEditDialog(id: Long, studentName: String, hw: String)
    }
}