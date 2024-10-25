package com.dicoding.picodiploma.intermsubm1_2.ui.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.intermsubm1_2.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.intermsubm1_2.ui.main.MainActivity
import com.dicoding.picodiploma.intermsubm1_2.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMyButtonEnable()

        playAnimation()

        loginViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[LoginViewModel::class.java]
        loginViewModel.responseMessage.observe(this){ message ->
            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
            if (message == "Berhasil Login !"){
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            }
        }


        val edLoginPassword = binding.edLoginPassword
        edLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (edLoginPassword.isNotError){
                    setMyButtonEnable()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })



        binding.goToRegister.setOnClickListener{
            val intentRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegister)
        }

        //button request
        binding.myButton.setOnClickListener {
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()

            loginViewModel.getLogin(email, password)

        }


    }

    override fun onBackPressed() {
        finishAffinity()
    }

    private fun playAnimation(){
        val loginTitle = ObjectAnimator.ofFloat(binding.loginName, View.ALPHA, 1f).setDuration(500)
        val loginEmail = ObjectAnimator.ofFloat(binding.edLoginEmail, View.ALPHA, 1f).setDuration(500)
        val loginPass = ObjectAnimator.ofFloat(binding.edLoginPassword, View.ALPHA, 1f).setDuration(500)
        val loginButton = ObjectAnimator.ofFloat(binding.myButton, View.ALPHA, 1f).setDuration(500)
        val loginRegister = ObjectAnimator.ofFloat(binding.goToRegister, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(loginTitle,loginEmail,loginButton,loginPass,loginRegister)
            start()
        }
    }

    private fun setMyButtonEnable() {
        val resultEmail = binding.edLoginEmail.text
        val resultPass = binding.edLoginPassword.text
        binding.myButton.isEnabled = resultEmail != null && resultEmail.toString().isNotEmpty()
                && resultPass != null && resultPass.toString().isNotEmpty()
    }

}
