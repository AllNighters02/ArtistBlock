package com.mihad.artistblock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mihad.artistblock.fragments.DrawingFragment
import com.mihad.artistblock.fragments.HomeFragment
import com.mihad.artistblock.fragments.ProfileFragment
import com.mihad.artistblock.fragments.SettingsFragment
import com.parse.ParseUser

class MainActivity : AppCompatActivity() {

    lateinit var swipe : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->

            var fragmentToShow: Fragment? = null

            when (item.itemId) {

                R.id.action_home -> {
                    fragmentToShow = HomeFragment()

                }

                R.id.action_drawing -> {
                    fragmentToShow = DrawingFragment()

                }

                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()

                }

                R.id.action_settings -> {
                    fragmentToShow = SettingsFragment()

                }
            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }

            true
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_logout) {
            ParseUser.logOut()
            val currentUser = ParseUser.getCurrentUser()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}