package id.telesandi.lks.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout
import id.telesandi.lks.MainActivity
import id.telesandi.lks.R
import id.telesandi.lks.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var textUsername: TextInputLayout
    private lateinit var textPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        // hilangkan actionbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // logic ketika klik tombol daftar
        binding.btnDaftar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // deklarasi inputan untuk handling error
        textUsername = binding.username
        textPassword = binding.password

        // ambil nilai input
        val username = binding.inputUsername.text.toString()
        val password = binding.inputpassword.text.toString()

        // logic ketika klik tombol login
        binding.btnLogin.setOnClickListener {
            if(username.isEmpty()) {
                textUsername.error = "username tidak boleh kosong!"
                textUsername.editText?.setOnClickListener {
                    textUsername.error = null
                }
            }

            if (password.isEmpty()) {
                textPassword.error = "password tidak boleh kosong!"
                textPassword.editText?.setOnClickListener {
                    textPassword.error = null
                }
            }

            if (username.isEmpty() && password.isEmpty()) {
                textUsername.error = "username tidak boleh kosong!"
                textUsername.editText?.setOnClickListener {
                    textUsername.error = null
                }
                textPassword.error = "password tidak boleh kosong!"
                textPassword.editText?.setOnClickListener {
                    textPassword.error = null
                }
            }

            if (username == "Nopal" && password == "Tels") {
                startActivity(Intent(this, MainActivity::class.java))
            } else if (!username.equals("Nopal") && !password.equals("Tels")) {
                Toast.makeText(this, username, Toast.LENGTH_LONG).show()
            }
        }

    }
}