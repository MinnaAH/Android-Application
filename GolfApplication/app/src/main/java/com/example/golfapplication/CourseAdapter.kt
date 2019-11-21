package com.example.golfapplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.json.JSONArray
import kotlinx.android.synthetic.main.course_item.view.*

class CourseAdapter (private val courses: JSONArray) : RecyclerView.Adapter<CourseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.course_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = courses.length()

    override fun onBindViewHolder(holder: CourseAdapter.ViewHolder, position: Int) {
        val course =  courses.getJSONObject(position)
        val image: String = "http://ptm.fi/materials/golfcourses/" + course["image"].toString()

        Glide.with(holder.pictureImageView.context)
            .load(image)
            .into(holder.pictureImageView)

        holder.courseTextView.text = course["course"].toString()
        holder.addressTextView.text = course["address"].toString()
        holder.phoneTextView.text = course["phone"].toString()
        holder.emailTextView.text = course["email"].toString()

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseTextView: TextView = view.courseView
        val addressTextView: TextView = view.addressView
        val phoneTextView: TextView = view.phoneView
        val emailTextView: TextView = view.emailView
        var pictureImageView: ImageView = view.imageView

        init{
            itemView.setOnClickListener {
                val intent = Intent(view.context, CourseActivity::class.java)
                intent.putExtra("course",courses[adapterPosition].toString())
                view.context.startActivity(intent)
            }
        }
    }
}