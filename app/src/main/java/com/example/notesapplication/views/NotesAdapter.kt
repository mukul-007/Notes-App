package com.example.notesapplication.views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplication.R
import com.example.notesapplication.databinding.NoteItemLayoutBinding
import com.example.notesapplication.models.Note
import java.util.Random

class NotesAdapter : ListAdapter<Note, NotesAdapter.NoteViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteViewHolder(var binding : NoteItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note?) {
            binding.titleTV.text = note?.title
            binding.contentTV.text = note?.content
            val random  = Random()
            val color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255))
            binding.view.setBackgroundColor(color)

            itemView.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(note)
                it.findNavController().navigate(direction)
            }
        }

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.equals(newItem)
        }

    }
}