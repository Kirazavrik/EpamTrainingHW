package com.gmail.epamtraininghw

import android.content.Context
import android.support.annotation.UiThread
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.student_item_view.view.*

class StudentItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        initializeView()
    }

    private fun initializeView() {
        View.inflate(context, R.layout.student_item_view, this)
        gravity = Gravity.BOTTOM
    }

    public fun setStudentName(name: String) {
        studentNameTextView.text = name
    }
}