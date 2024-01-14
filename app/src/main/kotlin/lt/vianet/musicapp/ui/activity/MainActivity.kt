package lt.vianet.musicapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import lt.vianet.musicapp.R
import lt.vianet.musicapp.databinding.ActivityMainBinding
import lt.vianet.musicapp.modules.data.storage.memoryStorage.MemoryStorage
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var memoryStorage: MemoryStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    override fun onStop() {
        memoryStorage.clearSharedPreferences()
        super.onStop()
    }

    override fun onDestroy() {
        memoryStorage.clearSharedPreferences()
        super.onDestroy()
    }
}
