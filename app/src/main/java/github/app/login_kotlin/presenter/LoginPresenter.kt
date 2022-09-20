package github.app.login_kotlin.presenter

import android.app.Activity
import android.content.Context
import android.widget.Toast
import github.app.login_kotlin.`interface`.LoginInterface
import github.app.login_kotlin.data.Database
import github.app.login_kotlin.model.User
import java.sql.SQLException

class LoginPresenter(activity: Activity) : LoginInterface.Presenter {

    private lateinit var view: LoginInterface.View
    private var listUser: ArrayList<User>


    init {
        listUser = getListUser(activity)
    }

    fun setView(view: LoginInterface.View) {
        this.view = view
    }

    override fun handleSignIn(user: User, context: Context) {
        if (user.isValidEmail() && user.isValidPassword()) {
            for (i in 0 until listUser.size) {
                if (user.email.equals(listUser[i].email)) {
                    if (user.password == listUser[i].password)
                        view.loginSuccess(context)
                    else
                        view.loginPasswordWrong()
                    break
                } else
                    view.loginEmailNotExit()
            }
        } else {
            if (!user.isValidEmail() && !user.isValidPassword())
                view.loginError()
            else if (!user.isValidEmail())
                view.loginEmailError()
            else if (!user.isValidPassword())
                view.loginPasswordError()
        }
    }

    private fun getListUser(activity: Activity): ArrayList<User> {
        val sql = "select * from user"
        val lisUser = ArrayList<User>()
        try {
            val database = Database.initDatabase(activity)
            val cursor = database.rawQuery(sql, null)
            for (i in 0 until cursor.count) {
                cursor.moveToPosition(i)
                val user = User()
                user.email = cursor.getString(1)
                user.password = cursor.getString(2)
                lisUser.add(user)
            }
        } catch (e: SQLException) {
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }

        return lisUser
    }


}