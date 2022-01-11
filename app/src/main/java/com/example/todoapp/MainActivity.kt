package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val listOfTasks = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       findViewById<Button>(R.id.button).setOnClickListener()
//       {
//
//       }
        // Look up recycler view in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TaskItemAdapter(listOfTasks)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}