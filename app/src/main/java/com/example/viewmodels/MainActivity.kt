package com.example.viewmodels

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.doggone.R
import com.example.doggone.databinding.ActivityMainBinding
import com.example.doggone.network.DogPhoto

class MainActivity : AppCompatActivity(){
    val viewModel: DogViewModel by viewModels {
        DogPhotoViewModelFactory((application as DogApplication).database.dogPhotoDao())
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.dogPhoto.observe(this, {
                findViewById<ImageView>(R.id.imageView).load(
                    it?.imageUrl!!.toUri().buildUpon().scheme("https").build()
                )
            })

        findViewById<Button>(R.id.button).setOnClickListener {
            val currentImgUrl = viewModel.dogPhoto.value?.imageUrl
            val newDogImage = DogPhoto(imageUrl = currentImgUrl)

            viewModel.getNewPhoto()
            viewModel.addDogImage(newDogImage)
            viewModel.deleteMostRecentDog()
        }

            findViewById<Button>(R.id.previous).setOnClickListener {
                //1. getAllDog history and cache it to the database to a new column
                //2. create a function to access the previous dog history in order
                //3. setOnClickListener on previous button to fetch database history
                //val previousImgUrl
            }
        findViewById<RecyclerView>(R.id.recycler_view)
        }
    }
