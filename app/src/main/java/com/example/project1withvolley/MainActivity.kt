package com.example.project1withvolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.project1withvolley.adapter.UserAdapter
import com.example.project1withvolley.databinding.ActivityMainBinding
import com.example.project1withvolley.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var requestQueue: RequestQueue

    private var url ="https://api.github.com/users"
    private var TAG = "MainActivity"

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)
        VolleyLog.DEBUG = true

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, object: Response.Listener<JSONArray> {
            override fun onResponse(response: JSONArray?) {
                val type = object : TypeToken<List<User>>(){}.type
                val list: List<User> = Gson().fromJson(response.toString(), type)

                userAdapter = UserAdapter(list)
                binding.rvMain.adapter = userAdapter

            }


        },
        object : Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {

            }

        })


        requestQueue.add(jsonArrayRequest)
    }
}