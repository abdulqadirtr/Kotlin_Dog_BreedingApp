package com.example.kotlindogbreed.ui.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindogbreed.R
import com.example.kotlindogbreed.adapter.BreedListAdapter
import com.example.kotlindogbreed.breedViewModel.BreedsViewModel
import com.example.kotlindogbreed.databinding.BreedListFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.breed_list_fragment.*


class BreedListFragment : Fragment() {

    companion object {
        fun newInstance() = BreedListFragment()
    }

    private lateinit var adapter: BreedListAdapter
    private lateinit var dataBinding: BreedListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = BreedListFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProviders.of(this@BreedListFragment).get(BreedsViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        val bottom_navigation = dataBinding.bottomNavigation.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    findNavController().navigate(R.id.action_breedListFragment_to_breedOfflineFragment, null)
                }
            }
            false
        })

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        dataBinding.viewmodel?.allBreeds?.observe(viewLifecycleOwner, Observer {
            if (it != null)
                adapter.updateBreedList(it)
        })

    }

    private fun setupAdapter() {
        val viewModel = dataBinding.viewmodel
        if (viewModel != null) {
            adapter = BreedListAdapter(viewModel!!)
            val layoutManager = LinearLayoutManager(activity)
            breed_list_rv.layoutManager = layoutManager
            breed_list_rv.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    layoutManager.orientation
                )
            )
            breed_list_rv.adapter = adapter
        }
    }

}
