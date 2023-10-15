package com.example.notesapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentHomeBinding
import com.example.notesapplication.models.Note
import com.example.notesapplication.viewModel.MainViewModel

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var binding : FragmentHomeBinding
    lateinit var adapter: NotesAdapter
    lateinit var notes : List<Note>
    lateinit var notesViewModel : MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initViews()
//        setHasOptionsMenu(true)
        return binding.root
    }

    fun initViews(){
        notesViewModel = (activity as MainActivity).noteViewModel

        binding.addNewBTN.setOnClickListener {
            goToAddNewNote(it)
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        notes = listOf()
        adapter = NotesAdapter()
        adapter.submitList(notes)
        binding.notesRV.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = adapter
            setHasFixedSize(true)
        }
        notesViewModel.getNotes("").observe(
            viewLifecycleOwner, Observer {
                notes = it
                adapter.submitList(notes)
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)
        val menuSearch = menu.getItem(R.id.menuSearch).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    private fun goToAddNewNote(view: View){
        view.findNavController().navigate(R.id.action_homeFragment_to_addNewFragment)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if(p0 != null){
            searchNotes(p0)
        }
        return true
    }

    private fun searchNotes(query : String){
        val searchQuery = "%$query"
        notesViewModel.searchNotes(searchQuery).observe(viewLifecycleOwner, Observer {
            notes = it
            adapter.submitList(notes)
        })
    }
}