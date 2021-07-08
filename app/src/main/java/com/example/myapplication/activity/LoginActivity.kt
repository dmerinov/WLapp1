package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val USER = "usuario"
        private const val PASSWORD = "123456"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerListeners()
    }

    private fun registerListeners() {
        reset.setOnClickListener { resetFields() }
        submit.setOnClickListener { validate(username.text.toString(),password.text.toString()) }
    }

    private fun resetFields() {
        username.setText("")
        password.setText("")
    }

    private fun validate(username: String, password: String) {
        if (username.equals(USER) && password.equals(PASSWORD)) {
            val intent = Intent(this, ListElementsActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this@LoginActivity, "Incorrect password. Try Again", Toast.LENGTH_LONG).show()
        }
    }

}