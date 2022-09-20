package github.app.login_kotlin.model

import android.text.TextUtils
import android.util.Patterns

class User {
    private lateinit var _email: String
    var email: String
        get() = _email
        // set(value) = {_email=value} //error: Type mismatch.Required: Unit//Found://() → Unit
        set(value) {
            _email = value
        }
    private lateinit var _password: String
    var password: String
        get() = _password
        set(value) {
            _password = value
        }

    constructor()
    constructor(_email: String, _password: String) {
        this._email = _email
        this._password = _password
    }

    fun isValidEmail(): Boolean {
        val isEmpty = !TextUtils.isEmpty(_email)
        val isValid = Patterns.EMAIL_ADDRESS.matcher(_email).matches()
        return isEmpty && isValid
    }

    fun isValidPassword(): Boolean {
        val isEmpty = !TextUtils.isEmpty(_password)
        val isValid = _password.length >= 6
        return isEmpty && isValid
    }


}