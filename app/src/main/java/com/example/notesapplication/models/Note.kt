package com.example.notesapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String,
    var color : String
)
