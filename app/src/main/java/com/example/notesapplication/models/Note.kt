package com.example.notesapplication.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "Notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String,
) : Parcelable
