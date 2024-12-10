package com.example.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.lista.R

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var listViewTasks: ListView

    private val tasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`todoapp_main.xml`)

        editTextTask = findViewById(/* id = */ R.id.editTextTask)
        buttonAddTask = findViewById(/* id = */ R.id.buttonAddTask)
        listViewTasks = findViewById(R.id.listViewTasks)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)
        listViewTasks.adapter = adapter

        buttonAddTask.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotBlank()) {
                tasks.add(task)
                editTextTask.text.clear()
                adapter.notifyDataSetChanged()
            }
        }

        listViewTasks.setOnItemLongClickListener { _, _, position, _ ->
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }
    }
}
