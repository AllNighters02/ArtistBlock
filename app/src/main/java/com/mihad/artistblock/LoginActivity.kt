package com.mihad.artistblock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            loginUser(username, password)
        }

        findViewById<Button>(R.id.btnSignup).setOnClickListener {
            goToSignUpActivity()
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "successfully logged in user")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToSignUpActivity() {
        //
        try {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            Log.i(TAG2, "going to signup")
        } catch (e: Exception) {
            Log.e(TAG2, "Failed")
            e.printStackTrace()
        }
        //Log.i(TAG2, "successfully went to signup")
        //finish()
    }

    companion object {
        const val TAG = "LoginActivity"
        const val TAG2 = "SignUpActivity"
    }

}