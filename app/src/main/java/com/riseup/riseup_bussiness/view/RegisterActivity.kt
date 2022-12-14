package com.riseup.riseup_bussiness.view

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.riseup.riseup_bussiness.databinding.ActivityRegisterBinding
import com.riseup.riseup_bussiness.util.EmailAlreadyExistsDialog
import com.riseup.riseup_bussiness.util.ErrorDialog
import com.riseup.riseup_bussiness.util.SuccessfulRegisterDialog
import com.riseup.riseup_bussiness.viewmodel.AuthResult
import com.riseup.riseup_bussiness.viewmodel.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import java.util.*

class RegisterActivity : AppCompatActivity() {

    val viewmodel: RegisterViewModel by viewModels()

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel.authState.observe(this){
            when(it.result){
                AuthResult.IDLE ->{

                }
                AuthResult.SUCCESS->{

                    val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
                    sp.edit().clear().apply()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).putExtra("Dialog","showDialog"))
                    Log.e(">>>","Hice la petición al dialogo")

                }
                AuthResult.FAIL->{
                    when(it.message){

                        "RepeatedEmail"-> {
                            EmailAlreadyExistsDialog().show(supportFragmentManager,"emailAlreadyExistDialog")}
                        "WeakPass"-> {

                            val dialogFragmentE = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","WeakPassword")
                            dialogFragmentE.arguments = bundle
                            dialogFragmentE.show(supportFragmentManager,"WeakPassDialog")
                        }
                        "invalidEmail"-> {

                            val dialogFragmentE = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","invalidEmail")
                            dialogFragmentE.arguments = bundle
                            dialogFragmentE.show(supportFragmentManager,"invalidEmailDialog")
                        }
                    }

                }
            }
        }

        binding.returnToLoginButton.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        binding.regBtn.setOnClickListener {

            if(binding.cellPhoneRegTF.text.isEmpty() ||  binding.emailRegTF.text.isEmpty()
                 ||  binding.passwordRegTF.text.isEmpty() || binding.confPassRegTF.text.isEmpty()){
                val dialogFragmentE = ErrorDialog()
                val bundle = Bundle()
                bundle.putString("TEXT","EmptyFields")
                dialogFragmentE.arguments = bundle
                dialogFragmentE.show(supportFragmentManager,"EmptyFieldsDialog")
            }else{

                var validate = viewmodel.validatePassword(binding.passwordRegTF.text.toString(),binding.confPassRegTF.text.toString())
                if(validate){
                    regBtnAction()
                }else{
                    val dialogFragmentE = ErrorDialog()
                    val bundle = Bundle()
                    bundle.putString("TEXT","PasswordNotMatch")
                    dialogFragmentE.arguments = bundle
                    dialogFragmentE.show(supportFragmentManager,"PasswordsDoesntMatchDialog")
                }
            }
        }

    }
    fun regBtnAction(){


        viewmodel.signUp(binding.emailRegTF.text.toString(), binding.passwordRegTF.text.toString()
        )


    }
}