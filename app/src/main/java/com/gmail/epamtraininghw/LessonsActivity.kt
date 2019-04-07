package com.gmail.epamtraininghw

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.git_lesson.*
import kotlinx.android.synthetic.main.git_lesson.view.*

class LessonsActivity : AppCompatActivity() {

    private lateinit var androidStudioLayout: RelativeLayout
    private lateinit var javaBasicLesson: RelativeLayout
    lateinit var cleanCodeLesson: RelativeLayout
    lateinit var componentsLesson: RelativeLayout
    private lateinit var container: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        container = findViewById(R.id.rootView)

        lesson_components_root_view.setOnClickListener {
            openLesson(MainActivity::class.java)
        }

        initLayout()

    }

    private fun setLayoutParams(view: RelativeLayout,
                                theme: String,
                                mentorName: String,
                                lessonDate: String) {

        view.lesson_topic.text = theme
        view.lesson_mentor.text = mentorName
        view.lesson_date.text = lessonDate
    }

    private fun prepareLayout(): RelativeLayout {
        var layout = layoutInflater.inflate(R.layout.git_lesson, null) as RelativeLayout
        container.addView(layout,2)

        return layout
    }

    private fun initLayout() {
        componentsLesson = prepareLayout()
        cleanCodeLesson = prepareLayout()
        javaBasicLesson = prepareLayout()
        androidStudioLayout = prepareLayout()
        setLayoutParams(androidStudioLayout, "Android studio", "Dima", "22.02.2019")
        setLayoutParams(javaBasicLesson, "Java Basic", "Ilya", "25.02.2019")
        setLayoutParams(cleanCodeLesson, "Clean Code", "Egor", "28.02.2019")
        setLayoutParams(componentsLesson, "Components", "Artem", "04.03.2019")

    }

    private fun openLesson(lessonActivityClass: Class<out Activity>) {
        startActivity(Intent(this, lessonActivityClass))
    }
}