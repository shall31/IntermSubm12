package com.dicoding.picodiploma.intermsubm1_2.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.intermsubm1_2.utils.ViewModelFactory
import com.dicoding.picodiploma.intermsubm1_2.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMyButtonEnable()



        //viewmodel
        registerViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[RegisterViewModel::class.java]
        registerViewModel.responseMessage.observe(this){ message ->
            Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT ).show()
        }


        //tempat untuk request
        binding.myButton.setOnClickListener {

            //mengambil username, email, dan password
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()

            //melakukan request
            registerViewModel.getRegistry(name, email, password)
        }

        binding.edRegisterPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.edRegisterPassword.isNotError){
                    setMyButtonEnable()
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.goToLogin.setOnClickListener{
            val intentRegister = Intent(this, LoginActivity::class.java)
            startActivity(intentRegister)
        }


    }

    private fun setMyButtonEnable() {
        val resultName = binding.edRegisterName.text
        val resultEmail = binding.edRegisterEmail.text
        val resultPass = binding.edRegisterPassword.text
        binding.myButton.isEnabled = resultEmail != null && resultEmail.toString().isNotEmpty()
                && resultName != null && resultName.toString().isNotEmpty()
                && resultPass != null && resultPass.toString().isNotEmpty()
    }



}