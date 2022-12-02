package com.riseup.riseup_users.repo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.riseup.riseup_bussiness.model.ShoppingCar


class SharedPreferences(context: Context) {

    val context: Context = context


    fun loadUser(): ShoppingCar? {
        val sp = context.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, ShoppingCar::class.java)
        }
    }



}