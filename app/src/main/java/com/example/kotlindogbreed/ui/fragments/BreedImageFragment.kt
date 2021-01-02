package com.example.kotlindogbreed.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.kotlindogbreed.R
import com.example.kotlindogbreed.breedViewModel.BreedImageViewModel
import com.example.kotlindogbreed.databinding.BreedImageFragmentBinding
import com.example.kotlindogbreed.room.Product
import com.example.kotlindogbreed.ui.MainActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.breed_image_fragment.*
import java.io.File
import java.lang.Exception

class BreedImageFragment : Fragment() {

    companion object {
        fun newInstance() = BreedImageFragment()
    }

    private lateinit var dataBinding: BreedImageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = BreedImageFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProviders.of(this@BreedImageFragment).get(BreedImageViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val breedName = arguments?.let {
            BreedImageFragmentArgs.fromBundle(
                it
            ).breedName
        }
        val subBreedName = arguments?.let {
            BreedImageFragmentArgs.fromBundle(
                it
            ).subBreedName
        }



        dataBinding.btnFvrtt.setOnClickListener(View.OnClickListener {
            val product = Product()
            product.fName = breedName
            product.lName = subBreedName
            MainActivity.appDatabase!!.productDao().addProduct(product)
            Toast.makeText(requireContext(),"Added to the the database" , Toast.LENGTH_LONG ).show()

        })
     /*   if (MainActivity.appDatabase!!.productDao().isFavorite(breedName) == 0) {
            btnFvrt.setBackgroundResource(R.drawable.notfvrt)
        } else {
            btnFvrt.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }*/


        dataBinding.breedName.text = breedName
        var queryRandomImage = "" + breedName
        if (!subBreedName.isNullOrEmpty()) {
            queryRandomImage += (File.separator + subBreedName)
        }
        getRandomImage(queryRandomImage)
    }

    private fun getRandomImage(queryRandomImage: String) {
        dataBinding.viewmodel?.breedImage(queryRandomImage)?.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it).into(breed_img, object : Callback {
                override fun onSuccess() {
                    dataBinding.viewmodel?.dataLoading?.postValue(false)
                }
                override fun onError(e: Exception?) {
                    dataBinding.viewmodel?.dataLoading?.postValue(false)
                }

            });

        })

    }

}
