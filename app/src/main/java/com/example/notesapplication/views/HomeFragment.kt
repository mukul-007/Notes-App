package com.example.notesapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentHomeBinding
import com.example.notesapplication.models.Note
import com.example.notesapplication.viewModel.MainViewModel

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var binding : FragmentHomeBinding
    lateinit var notesAdapter: NotesAdapter
    lateinit var notes : List<Note>
    lateinit var notesViewModel : MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initViews()
        setHasOptionsMenu(true)
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
//        notes = listOf()
        notesAdapter = NotesAdapter()
//        adapter.submitList(notes)
        binding.notesRV.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(false)
            this.adapter = notesAdapter
        }
        notesViewModel.notesLiveData.observe(
            viewLifecycleOwner, Observer {
                it?.let {
//                    notes = it
//                    adapter.notifyDataSetChanged()
                    notesAdapter.submitList(it)
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)
        val menuSearch = menu.findItem(R.id.menuSearch).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun goToAddNewNote(view: View){
        view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNewFragment())
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
        val searchQuery = "%$query%"
        notesViewModel.searchNotes(searchQuery)
//        notesViewModel.searchNotes(searchQuery).observe(viewLifecycleOwner, Observer {
//            notes = it
//            adapter.submitList(notes)
//        })
    }
}