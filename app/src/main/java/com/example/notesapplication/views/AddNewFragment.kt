package com.example.notesapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentAddNewBinding
import com.example.notesapplication.models.Note
import com.example.notesapplication.viewModel.MainViewModel

class AddNewFragment : Fragment() {
    private lateinit var binding : FragmentAddNewBinding
    private lateinit var notesViewModel : MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new, container, false)
        notesViewModel = (activity as MainActivity).noteViewModel
        setHasOptionsMenu(true)
        return binding.root
    }

    fun saveNote(){
        val title = binding.titleET.text.toString()
        val content = binding.contentET.text.toString()
        if(title.isNotEmpty()){
            val note = Note(0, title, content)
            notesViewModel.addNote(note)
            Toast.makeText(context, "Note created successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNewFragment_to_homeFragment)
        }else{
            Toast.makeText(context, "Please Enter title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.save_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuSave->{
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}