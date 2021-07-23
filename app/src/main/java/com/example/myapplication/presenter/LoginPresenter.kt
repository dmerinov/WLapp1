package com.example.myapplication.presenter

class LoginPresenter(private val view: LoginView) {

    companion object {
        private const val USER = "usuario"
        private const val PASSWORD = "123456"
    }

    fun initialize() {
    }

    fun onResetClick() {
        view.resetFields()
    }

    fun onSubmitClick() {
        if (isUserValid(view.getUser(), view.getPassword())) {
            view.navigateToMonumentsScreen()
        } else {
            view.showLoginError()
        }
    }

    fun onCheckboxClick() {
        if (view.getCheckboxStatus()) {
            view.showPassword()
        } else {
            view.hidePassword()
        }

    }

    private fun isUserValid(username: String, password: String): Boolean =
        username == USER && password == PASSWORD

}

interface LoginView {
    fun resetFields()
    fun getCheckboxStatus(): Boolean
    fun navigateToMonumentsScreen()
    fun showPassword()
    fun hidePassword()
    fun showLoginError()
    fun getUser(): String
    fun getPassword(): String
}