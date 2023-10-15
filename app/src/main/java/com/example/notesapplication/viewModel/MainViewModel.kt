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

    init {
        notes.postValue(repository.getNotes().value)
    }
    val notesLiveData : LiveData<List<Note>>
        get() = notes
    fun addNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }

    fun updateNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun getNotes(query : String) : LiveData<List<Note>>{
        notes.postValue(repository.getNotes().value)
        return notesLiveData
    }

    fun searchNotes(query : String) : LiveData<List<Note>>{
        return repository.searchNote(query)
    }

}