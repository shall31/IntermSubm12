package com.dicoding.picodiploma.intermsubm1_2.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.intermsubm1_2.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.intermsubm1_2.response.Story
import com.dicoding.picodiploma.intermsubm1_2.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    private var detailId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[DetailViewModel::class.java]
        detailViewModel.detail.observe(this){
            setUserData(it)
        }

        detailId = if (Build.VERSION.SDK_INT >= 33) {
            intent.getStringExtra("key_users") ?: ""
        }else {
            @Suppress("DEPRECATION")
            intent.getStringExtra("key_users") ?: ""
        }

        detailViewModel.getDetail(detailId)



    }

    private fun setUserData(user: Story){

        Glide.with(binding.ivDetailPhoto).load(user.photoUrl).into(binding.ivDetailPhoto)
        binding.tvDetailName.text = user.name
        binding.tvCreated.text = user.createdAt
        binding.tvDetailDescription.text = user.description

    }
}