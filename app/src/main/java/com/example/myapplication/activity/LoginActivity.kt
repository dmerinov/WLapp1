package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.presenter.LoginPresenter
import com.example.myapplication.presenter.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter: LoginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        registerListeners()
        presenter.initialize()
    }

    private fun registerListeners() {
        reset.setOnClickListener { presenter.onResetClick() }
        submit.setOnClickListener { presenter.onSubmitClick() }
        checkBox.setOnClickListener { presenter.onCheckboxClick() }

    }

    override fun resetFields() {
        username.setText("")
        password.setText("")
    }

    override fun getCheckboxStatus(): Boolean {
        return checkBox.isChecked
    }

    override fun navigateToMonumentsScreen() {
        val intent = Intent(this, MonumentsActivity::class.java)
        startActivity(intent)
    }

    override fun showPassword() {
        password.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    override fun hidePassword() {
        password.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun showLoginError() {
        Toast.makeText(this@LoginActivity, "Incorrect credentials. Try Again", Toast.LENGTH_LONG)
            .show()
    }

    override fun getUser(): String {
        return username.text.toString()
    }

    override fun getPassword(): String {
        return password.text.toString()
    }

}