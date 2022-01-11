package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val onLongClickListener = object:TaskItemAdapter.OnLongClickListener
        {
            override fun onItemLongClicker(position: Int) {
                // remove the item from list
                listOfTasks.removeAt(position)

                // notify adapter that something has changed
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }
        loadItems()
        // Look up recycler view in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // set up button and input field so that the user can enter a task and add it to the list
        // grabbing reference to the button
        // and then set an onclick listener
        findViewById<Button>(R.id.button).setOnClickListener()
        {
            // 1. grab the text that the user has inputted into the text field
            val userText = findViewById<EditText>(R.id.addTaskField).text.toString()
            // 2. add the string to our list of tasks: listOfTasks
            listOfTasks.add(userText)

            //notify the adapter that data has been updated
            adapter.notifyItemInserted(listOfTasks.size-1)

            // 3. reset the text field
            findViewById<EditText>(R.id.addTaskField).setText("")
            saveItems()
        }

    }
    // save the data that the user has inputted
    // save data by writing and reading from a file


    // create a method to get the file we need
    fun getDataFile(): File {
        // every line is going to represent a specific task in our list of tasks
        return File(filesDir, "data.txt")
    }

    // load the items by reading every line in the file
    fun loadItems()
    {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch(ioException:IOException)
        {
            ioException.printStackTrace()
        }
    }

    // save all our items = writing things to our file

    fun saveItems()
    {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        }catch(ioException:IOException)
        {
            ioException.printStackTrace()
        }
    }
}