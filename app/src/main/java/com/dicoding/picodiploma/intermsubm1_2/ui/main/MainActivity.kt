package com.dicoding.picodiploma.intermsubm1_2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.intermsubm1_2.R
import com.dicoding.picodiploma.intermsubm1_2.databinding.ActivityMainBinding
import com.dicoding.picodiploma.intermsubm1_2.ui.addstory.AddStoryActivity
import com.dicoding.picodiploma.intermsubm1_2.ui.auth.LoginActivity
import com.dicoding.picodiploma.intermsubm1_2.ui.map.MapsActivity

import com.dicoding.picodiploma.intermsubm1_2.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(this)
    }

    private val preferenceHelper = PreferenceHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMain.layoutManager = LinearLayoutManager(this)

        //sebenarnya bisa diletakkan didalam viewmodel. karena waktu terbatas, ntaran aja diimplementasikan di projek akhir

        val token = preferenceHelper.getToken()
        if (token == null){
            Toast.makeText(this@MainActivity, "Tidak ada token", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        getData()




        //Floating Action Button
        binding.fab.setOnClickListener {
            //harus berpindah ke addstoryactivity
            startActivity(Intent(this@MainActivity, AddStoryActivity::class.java))
            finish()
        }

        binding.fabMaps.setOnClickListener {
            //menuju map activity
            startActivity(Intent(this@MainActivity, MapsActivity::class.java))
            finish()
        }



    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logout -> {
                preferenceHelper.clearToken()
                finishAffinity()
            }
        }
        return true
    }

    private fun getData() {
        val adapter = MainAdapter()
        binding.rvMain.adapter = adapter
        mainViewModel.quote.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }


}