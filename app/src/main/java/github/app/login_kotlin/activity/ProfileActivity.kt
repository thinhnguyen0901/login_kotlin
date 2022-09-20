package github.app.login_kotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import github.app.login_kotlin.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addEvents()
    }

    private fun addEvents() {
        binding.imvLogout.setOnClickListener {
                it.setOnClickListener {
                    finish()
                    startActivity(Intent(this,LoginActivity::class.java))
                }
        }
    }
}


