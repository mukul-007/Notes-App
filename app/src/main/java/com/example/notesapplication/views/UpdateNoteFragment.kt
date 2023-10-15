package com.example.notesapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentUpdateNoteBinding
import com.example.notesapplication.models.Note
import com.example.notesapplication.viewModel.MainViewModel

class UpdateNoteFragment : Fragment() {

    private lateinit var binding : FragmentUpdateNoteBinding
    private lateinit var notesViewModel : MainViewModel
    private var currentNote: Note? = null
    private val args: UpdateNoteFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_note, container, false)
        notesViewModel = (activity as MainActivity).noteViewModel
        setHasOptionsMenu(true)
        currentNote = args.note
        initViews()
        return binding.root
    }

    private fun initViews(){
        binding.titleUpdateET.setText(currentNote?.title)
        binding.contentUpdateET.setText(currentNote?.content)
        binding.updateBTN.setOnClickListener {
            currentNote.let {
                val title = binding.titleUpdateET.text.toString()
                val content = binding.contentUpdateET.text.toString()
                if(title.isNotEmpty()){
                    val note = Note(currentNote!!.id, title, content)
                    notesViewModel.addNote(note)
                    Toast.makeText(context, "Note updated successfully!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addNewFragment_to_homeFragment)
                }else{
                    Toast.makeText(context, "Please Enter title", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuDelete->{
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        currentNote?.let {
            notesViewModel.deleteNote(it)
            Toast.makeText(context, "Note Deleted Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}