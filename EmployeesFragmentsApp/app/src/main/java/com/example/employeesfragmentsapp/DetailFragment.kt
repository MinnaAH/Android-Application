package com.example.employeesfragmentsapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_detail.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // create view
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // get root view
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // show employee if there is a selection made in recycler view's adapter
        if (EmployeesAdapter.position != -1) {
            val employee = MainActivity.employees.getJSONObject(EmployeesAdapter.position)
            // show data in UI
            employee?.let {
                rootView.nameView.text = it.getString("lastName") + " " + it.getString("firstName")
                rootView.titleView.text = it.getString("title")
                rootView.emailView.text = it.getString("email")
                rootView.phoneView.text = it.getString("phone")
                rootView.departmentView.text = it.getString("department")
                val requestOptions = RequestOptions()
                requestOptions.override(500, 500)
                Glide.with(this).load(it.getString("image")).apply(requestOptions).into(rootView.imageView)
            }
        }
        // return view
        return rootView
    }
}
