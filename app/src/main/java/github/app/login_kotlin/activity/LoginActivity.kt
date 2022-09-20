package github.app.login_kotlin.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import github.app.login_kotlin.`interface`.LoginInterface
import github.app.login_kotlin.databinding.ActivityLoginBinding
import github.app.login_kotlin.model.User
import github.app.login_kotlin.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginInterface.View {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPresenter()
    }

    private fun initPresenter() {
        presenter = LoginPresenter(this)
        presenter.setView(this)
    }


    override fun loginError() {
        binding.edtEmail.error = "Email không hợp lệ"
        binding.edtPassword.error = "Mật khẩu không hợp lệ"
    }

    override fun loginPasswordError() {
        binding.edtPassword.error = "Mật khẩu không hợp lệ"
    }

    override fun loginPasswordWrong() {
        binding.edtPassword.error = "Mật khẩu không đúng"
    }

    override fun loginEmailError() {
        binding.edtEmail.error = "Email không hợp lệ"
    }

    override fun loginEmailNotExit() {
        binding.edtEmail.error = "Email không tồn tại"
    }

    fun openProfile(view: View) {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val user = User(email, password)
        presenter.handleSignIn(user, this)
    }
}