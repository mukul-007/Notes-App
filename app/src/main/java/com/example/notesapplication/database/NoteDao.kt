package com.example.notesapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapplication.models.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note : Note)

    @Update
    suspend fun updateNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("Select * from Notes order by id desc")
    fun getNotes() : LiveData<List<Note>>

    @Query("Select * from Notes where title like :query or content like :query")
    fun searchNote(query: String?) : LiveData<List<Note>>
}