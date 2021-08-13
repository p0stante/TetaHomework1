package ru.avtoropova.tetahomework1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.avtoropova.tetahomework1.R

class MainActivity : AppCompatActivity() {
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setContentView(R.layout.activity_main)
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bnv = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bnv.setupWithNavController(navHostFragment.navController)
    }
}





