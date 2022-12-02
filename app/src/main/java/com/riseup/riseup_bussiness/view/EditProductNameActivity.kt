package com.riseup.riseup_bussiness.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_bussiness.databinding.ActivityEditProductImageBinding
import com.riseup.riseup_bussiness.databinding.ActivityEditProductNameBinding

class EditProductNameActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProductNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras?.get("Product")
    }

}