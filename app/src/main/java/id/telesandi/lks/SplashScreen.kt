package id.telesandi.lks

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import id.telesandi.lks.databinding.ActivitySplashScreenBinding
import id.telesandi.lks.ui.auth.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // hilangkan actionbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide() // Null Pointer Error... --> java || ? --> safe opration

        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // handler untuk menghilangkan status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        val splashTime: Long = 3000 // 3000 -> 3 detik

        // postdelay ke MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // panggil activity selanjutnya
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            // setelah activity selanjutnya terpanggil, maka destroy/kill splashscreen
            this.finish()
        }, splashTime)

    }
}