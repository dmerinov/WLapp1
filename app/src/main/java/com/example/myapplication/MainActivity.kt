package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting references to the views
        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_user_name.setText("")
            et_password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = et_user_name.text;
            val password = et_password.text.toString();
            var success = validatePass(password)

            // your code to validate the user_name and password combination
            // and verify the same

        }
    }

    fun validatePass(password : String) {
        var result = ""
        if(password.equals("123456")) {
            result = "Correct"
            Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
            val intent = Intent(this, ListaElementosActivity::class.java)
            startActivity(intent)
        } else {
            result = "Incorrect password. Try Again"
            Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
        }
    }
}