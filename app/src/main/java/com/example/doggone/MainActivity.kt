package com.example.doggone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.doggone.database.DogImageEntity
import com.example.doggone.viewmodels.DogPhotoViewModelFactory
import com.example.doggone.viewmodels.DogViewModel
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    private val viewModel: DogViewModel by viewModels {
        DogPhotoViewModelFactory((application as DogApplication).database.dogPhotoDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeDogButton: Button = findViewById(R.id.button)
        val prevDogButton :Button = findViewById(R.id.previous)

        viewModel.dogPhoto.observe(this, {
            val mainImage : ImageView = findViewById(R.id.imageView)
            Picasso.with(this).load(it.imgUrl).into(mainImage)
        })


        changeDogButton.setOnClickListener {
            val currentImgUrl = viewModel.dogPhoto.value?.imgUrl
            viewModel.getNewPhoto()
            val newDogImage = currentImgUrl?.let { it1 -> DogImageEntity(imageUrl = it1) }
            if (newDogImage != null) {
                viewModel.addDog(newDogImage)
            }
        }
            prevDogButton.setOnClickListener {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)


        }
    }
}