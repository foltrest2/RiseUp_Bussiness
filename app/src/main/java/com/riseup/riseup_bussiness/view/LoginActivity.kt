package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import com.google.gson.Gson
import com.riseup.riseup_bussiness.databinding.ActivityLoginBinding
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.model.ProductModel
import com.riseup.riseup_bussiness.util.ErrorDialog
import com.riseup.riseup_bussiness.util.SuccessfulRegisterDialog
import com.riseup.riseup_bussiness.viewmodel.AuthResult
import com.riseup.riseup_bussiness.viewmodel.LoginViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding
    val viewModel: LoginViewModel by viewModels()

    @OptIn(DelicateCoroutinesApi::class)
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

        viewModel.authState.observe(this){
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

                            val thisDiscoToSave = viewModel.saveUserFromViewModel()
                            saveDisco(thisDiscoToSave)
                            viewModel.loadProducts(thisDiscoToSave)
                            Log.e(">>>", "SAVED: $thisDiscoToSave")
                            startActivity(Intent(this@LoginActivity,OrdersListActivity::class.java))
                            finish()
                        }

                        "VerifiedFirstTime"->{

                            val thisDiscoToSave = viewModel.saveUserFromViewModel()
                            saveDisco(thisDiscoToSave)
                            viewModel.loadProducts(thisDiscoToSave)
                            Log.e(">>>", "SAVED: $thisDiscoToSave")
                            startActivity(Intent(this@LoginActivity,DiscoInitialConfigActivity::class.java))
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

        viewModel.inComingProduct.observe(this){
            Log.e(">>>", "Actualizado products en observer: ${it}")
            saveProducts(it)
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
            viewModel.signIn(binding.emailLoginTF.text.toString(),binding.loginPasswordTF.text.toString())
        }

    }

    fun showDialog(){
        SuccessfulRegisterDialog().show(supportFragmentManager,"successfullyRegister")
    }

    private fun saveDisco(disco: DiscoModel){
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = Gson().toJson(disco)
        sp.edit().putString("Usuario",json).apply()
    }

    private fun saveProducts(car: List<ProductModel>) {
        val sp = this.getSharedPreferences("RiseUpBusiness", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(car)
        sp?.edit()?.putString("Products", json)?.apply()
    }
}
