package com.mihad.artistblock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mihad.artistblock.Fragments.DrawingFragment
import com.mihad.artistblock.Fragments.HomeFragment
import com.mihad.artistblock.Fragments.ProfileFragment
import com.mihad.artistblock.Fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
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
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }

                R.id.action_drawing -> {
                    fragmentToShow = DrawingFragment()
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
                }

                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }

                R.id.action_settings -> {
                    fragmentToShow = SettingsFragment()
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }

            true
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home


    }

}