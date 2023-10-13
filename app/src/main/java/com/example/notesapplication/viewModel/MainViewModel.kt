package com.example.notesapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.notesapplication.repos.NoteRepository

class MainViewModel(var repository: NoteRepository) : ViewModel(){

}