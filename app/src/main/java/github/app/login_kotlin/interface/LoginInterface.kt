package github.app.login_kotlin.`interface`

import android.content.Context
import android.content.Intent
import github.app.login_kotlin.activity.ProfileActivity
import github.app.login_kotlin.model.User

interface LoginInterface {
    interface View {
        fun loginSuccess(context: Context) {
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        }
        fun loginError()
        fun loginPasswordError()
        fun loginPasswordWrong()
        fun loginEmailError()
        fun loginEmailNotExit()
    }

    interface Presenter {
        fun handleSignIn(user: User,context: Context)
    }
}