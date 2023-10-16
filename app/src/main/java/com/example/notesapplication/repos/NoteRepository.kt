package com.example.notesapplication.repos

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.notesapplication.database.NoteDao
import com.example.notesapplication.models.Note

class NoteRepository(var noteDao : NoteDao) {
    suspend fun insertNote(note : Note) = noteDao.insertNote(note)


    suspend fun updateNote(note : Note) = noteDao.updateNote(note)


    suspend fun deleteNote(note : Note) = noteDao.deleteNote(note)


    suspend fun getNotes() : List<Note> = noteDao.getNotes()


    suspend fun searchNote(query : String?): List<Note> = noteDao.searchNote(query)

}