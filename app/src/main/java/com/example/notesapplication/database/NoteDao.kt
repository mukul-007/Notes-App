package com.example.notesapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapplication.models.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note : Note)

    @Update
    suspend fun updateNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("Select * from Notes")
    fun getNotes() : LiveData<List<Note>>
}