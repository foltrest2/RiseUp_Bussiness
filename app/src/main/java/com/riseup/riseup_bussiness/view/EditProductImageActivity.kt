package com.riseup.riseup_bussiness.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_bussiness.databinding.ActivityEditProductImageBinding

class EditProductImageActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProductImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras?.get("Product")

    }
}