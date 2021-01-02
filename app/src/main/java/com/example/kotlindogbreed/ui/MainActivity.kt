package com.example.kotlindogbreed.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlindogbreed.R
import com.example.kotlindogbreed.room.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var view: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        //Instance of Room
        MainActivity.appDatabase = AppDatabase.getInstance(this)

            NavigationUI.setupActionBarWithNavController(
                this,
                findNavController(R.id.main_nav_fragment)
            )
    }


    // function for calling fragment
    fun AppCompatActivity.replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.main_nav_fragment, fragment, "breedOfflineFragment")
        transaction.addToBackStack("breedOfflineFragment")
        transaction.commit()
    }

    companion object {
        @JvmField
        var appDatabase: AppDatabase? = null
    }
}