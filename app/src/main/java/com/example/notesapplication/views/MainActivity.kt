package com.example.notesapplication.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplication.R
import com.example.notesapplication.database.NoteDatabase
import com.example.notesapplication.repos.NoteRepository
import com.example.notesapplication.viewModel.MainViewModel
import com.example.notesapplication.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NoteDatabase.getInstance(applicationContext)
        val repository = NoteRepository(db.getNoteDao())
        noteViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(MainViewModel::class.java)
    }
}