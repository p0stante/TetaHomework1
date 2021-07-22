package ru.avtoropova.tetahomework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.avtoropova.tetahomework1.fragments.MoviesListFragment
import ru.avtoropova.tetahomework1.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()

            .add(R.id.main_container, MoviesListFragment())
            .commit()

        val bnv = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bnv.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findViewById<View>(R.id.home_underline).visibility = View.VISIBLE
                    findViewById<View>(R.id.profile_underline).visibility = View.INVISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, MoviesListFragment()).addToBackStack(null)
                        .commit()
                    true
                }
                R.id.profile -> {
                    findViewById<View>(R.id.home_underline).visibility = View.INVISIBLE
                    findViewById<View>(R.id.profile_underline).visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, ProfileFragment()).addToBackStack(null)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}





