package com.mihad.artistblock

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseFile
import com.parse.ParseUser

class SignUpActivity  : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        findViewById<Button>(R.id.btnSignup).setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            //val profilePic = findViewById<ImageView>(R.id.ivProfile).
            signUpUser(username, password, email)
            goToMainActivity()
        }

    }

    private fun signUpUser(username: String, password: String, email: String) {
        // Create the ParseUser
        val user = User()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)
        user.setEmail(email)
        //user.setProfilePic(profilePic)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                e.printStackTrace()
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}