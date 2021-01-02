package com.example.kotlindogbreed.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindogbreed.R
import com.example.kotlindogbreed.adapter.DataAdapter
import com.example.kotlindogbreed.room.Product
import com.example.kotlindogbreed.ui.MainActivity

class OfflineFragment : Fragment() {
    private var items: List<Product>? = null
    var recyclerView: RecyclerView? = null
    var adapter: RecyclerView.Adapter<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var view =inflater.inflate(R.layout.fragment_offline, container, false)
       if( MainActivity.appDatabase!!.productDao().getAll() != null) {
           items = MainActivity.appDatabase!!.productDao().getAll()
           recyclerView = view.findViewById<View>(R.id.breed_list_rv) as RecyclerView
           val layoutManager = LinearLayoutManager(requireContext())
           layoutManager.orientation = LinearLayoutManager.VERTICAL
           recyclerView!!.layoutManager = layoutManager
           adapter = DataAdapter(requireContext(), items!!)
           (adapter as DataAdapter).notifyDataSetChanged()
           recyclerView!!.adapter = adapter
       }
else
       {
           Toast.makeText(requireContext(), "No values is stored", Toast.LENGTH_LONG).show()
       }


        return view
    }

}