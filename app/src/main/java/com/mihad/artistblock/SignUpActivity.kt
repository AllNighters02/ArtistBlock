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
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }
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
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)
        user.setEmail(email)

        user.signUpInBackground { e ->
            if (e == null) {
                //goToMainActivity()
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