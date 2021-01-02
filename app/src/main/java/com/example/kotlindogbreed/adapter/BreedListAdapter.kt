package com.example.kotlindogbreed.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindogbreed.R
import com.example.kotlindogbreed.breedViewModel.BreedsViewModel
import com.example.kotlindogbreed.databinding.BreedListItemBinding
import com.example.kotlindogbreed.room.Product
import com.example.kotlindogbreed.ui.MainActivity
import androidx.databinding.library.baseAdapters.BR;

class BreedListAdapter(private val breedViewModel: BreedsViewModel) :
    RecyclerView.Adapter<BreedListViewHolder>() {

    var breedList: MutableList<Pair<String, String>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = BreedListItemBinding.inflate(inflater, parent, false)
        return BreedListViewHolder(
            dataBinding,
            breedViewModel
        )
    }

    override fun getItemCount() = breedList.size

    override fun onBindViewHolder(holder: BreedListViewHolder, position: Int) {
        holder.setup(breedList[position], position)
    }

    fun updateBreedList(list: Map<String, List<String>>) {
        for (breed in list) {
            if (breed.value.isEmpty())
                breedList.add(Pair(breed.key, ""))
            else
                for (sub_breed in breed.value) {
                    breedList.add(Pair(breed.key, sub_breed))
                }
        }
        notifyDataSetChanged()
    }

}


class BreedListViewHolder constructor(
    private val dataBinding: ViewDataBinding,
    private val breedsViewModel: BreedsViewModel
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun setup(itemData: Pair<String, String>, position: Int) {
        var description =
            if (itemData.second.isEmpty()) itemData.first else itemData.first + " - " + itemData.second
        dataBinding.setVariable(BR.itemBreed, description)
        dataBinding.executePendingBindings()
        itemView.setOnClickListener(View.OnClickListener {
            val bundle = bundleOf("breedName" to itemData.first, "subBreedName" to itemData.second)
            itemView.findNavController()
                .navigate(R.id.action_breedListFragment_to_breedImageFragment, bundle)
        })
    }
}