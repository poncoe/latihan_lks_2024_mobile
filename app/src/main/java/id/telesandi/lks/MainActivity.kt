package id.telesandi.lks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import id.telesandi.lks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // inisialisasi
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // memanggil navcontroller di oncreate pada saat membuka layout
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    // untuk handle ketika fragment di back tidak stuck
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        when(navController.currentDestination?.id) {
            R.id.homeFragment -> {
                navController.navigate(R.id.homeFragment)
            }
            R.id.profileFragment -> {
                navController.navigate(R.id.profileFragment)
            }
        }
    }

    // Untuk mengseting tombol back di actionbar (navigation up)
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}