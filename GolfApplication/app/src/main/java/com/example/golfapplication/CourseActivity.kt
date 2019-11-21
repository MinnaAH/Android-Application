package com.example.golfapplication

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_course.*
import org.json.JSONObject

class CourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val courseString = bundle!!.getString("course")
            val course = JSONObject(courseString)

            courseView.text = course["course"].toString()
            addressView.text = course["address"].toString()
            phoneView.text = course["phone"].toString()
            emailView.text = course["email"].toString()
            infoView.text = course["text"].toString()
            webView.text = course["web"].toString()

            val image: String = "http://ptm.fi/materials/golfcourses/" + course["image"].toString()
            Glide.with(imageView.context)
                .load(image)
                .into(imageView)

            val latitude = course["lat"].toString()
            val longitude = course["lng"].toString()
            val location = Uri.parse("geo:$latitude,$longitude")

            locationView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, location)
                startActivity(intent)
            }

        }
    }
}
