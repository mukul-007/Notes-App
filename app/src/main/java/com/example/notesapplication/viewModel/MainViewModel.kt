package com.example.notesapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplication.models.Note
import com.example.notesapplication.repos.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(var repository: NoteRepository) : ViewModel(){

    private var notes : MutableLiveData<List<Note>> = MutableLiveData()

    val notesLiveData : LiveData<List<Note>> = notes
    init {
        fetchNotes()
    }
    fun addNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
            fetchNotes()
        }
    }

    fun updateNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
            fetchNotes()
        }
    }

    fun deleteNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
            fetchNotes()
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getNotes()
            notes.postValue(result)
        }
    }

//    fun getNotes() /*: LiveData<List<Note>>*/{
//        notes.postValue(repository.getNotes().value)
////        return notesLiveData
//    }

    fun searchNotes(query : String) /*: LiveData<List<Note>>*/{
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchNote(query)
            notes.postValue(result)
        }
    }

}