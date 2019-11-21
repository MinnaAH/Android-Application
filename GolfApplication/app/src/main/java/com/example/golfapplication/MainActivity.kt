package com.example.golfapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val queue = Volley.newRequestQueue(this)
        val url = "http://ptm.fi/materials/golfcourses/golf_courses.json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->

                val courses = response.getJSONArray("courses")

                recyclerView.adapter = CourseAdapter(courses)
            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )

        queue.add(jsonObjectRequest)

    }
}
