package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.gson.Gson
import com.riseup.riseup_bussiness.databinding.ActivityLoginBinding
import com.riseup.riseup_bussiness.model.Disco
import com.riseup.riseup_bussiness.util.ErrorDialog
import com.riseup.riseup_bussiness.util.SuccessfulRegisterDialog
import com.riseup.riseup_bussiness.viewmodel.AuthResult
import com.riseup.riseup_bussiness.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding
    val viewmodel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras?.get("Dialog")
        if (intent != null) {
            when (intent) {
                "showDialog" -> showDialog()
            }
        }

        viewmodel.authState.observe(this){
            when(it.result){
                AuthResult.IDLE ->{
                }
                AuthResult.SUCCESS->{
                    when(it.message){

                        "NotVerified"-> {
                            val dialogFragmentP = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","NotVerified")
                            dialogFragmentP.arguments = bundle
                            dialogFragmentP.show(supportFragmentManager,"notVerifiedDialog")

                        }
                        "Verified"->{

                            val thisDiscoToSave = viewmodel.saveUserFromViewModel()
                            saveDisco(thisDiscoToSave)
                            Log.e(">>>", "SAVED: $thisDiscoToSave")
                            startActivity(Intent(this@LoginActivity, OrdersListActivity::class.java))
                            finish()
                        }

                        "VerifiedFirstTime"->{

                            val thisDiscoToSave = viewmodel.saveUserFromViewModel()
                            saveDisco(thisDiscoToSave)
                            Log.e(">>>", "SAVED: $thisDiscoToSave")
                            startActivity(Intent(this@LoginActivity, DiscoInitialConfigActivity::class.java))
                            finish()
                        }
                    }
                }
                AuthResult.FAIL->{

                    when(it.message){
                        "wrongPassword"-> {
                            val dialogFragmentP = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","WrongPassword")
                            dialogFragmentP.arguments = bundle
                            dialogFragmentP.show(supportFragmentManager,"wrongPasswordDialog")
                        }

                        "invalidEmail"->{
                            val dialogFragmentE = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","InvalidEmail")
                            dialogFragmentE.arguments = bundle
                            dialogFragmentE.show(supportFragmentManager,"invalidEmailDialog")
                        }
                        "userNotFound"->{
                            val dialogFragmentU = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","UserNotFound")
                            dialogFragmentU.arguments = bundle
                            dialogFragmentU.show(supportFragmentManager,"userNotFoundDialog")
                        }
                        "networkError"->{
                            val dialogFragmentU = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","NetworkError")
                            dialogFragmentU.arguments = bundle
                            dialogFragmentU.show(supportFragmentManager,"networkError")
                        }
                    }
                }
            }
        }
        binding.userLoginBtn.setOnClickListener {
            logIn()

        }

        binding.registerHereTV.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

    }
    fun logIn(){
        if(binding.emailLoginTF.text.isEmpty() || binding.loginPasswordTF.text.isEmpty() ){

            val dialogFragmentE = ErrorDialog()
            val bundle = Bundle()
            bundle.putString("TEXT","EmptyFields")
            dialogFragmentE.arguments = bundle
            dialogFragmentE.show(supportFragmentManager,"EmptyFieldsDialog")
        }else{
            viewmodel.signIn(binding.emailLoginTF.text.toString(),binding.loginPasswordTF.text.toString())

        }

    }

    fun showDialog(){
        SuccessfulRegisterDialog().show(supportFragmentManager,"successfullyRegister")
    }

    private fun saveDisco(disco: Disco){
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = Gson().toJson(disco)
        sp.edit().putString("Usuario",json).apply()
    }
}
