package com.mihad.artistblock.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mihad.artistblock.R
import com.parse.ParseUser
import java.io.IOException


class SettingsFragment : Fragment() {

    lateinit var ivProfileCurrent: ImageView
    lateinit var tvUsernameCurrent: TextView
    lateinit var etNewUsername: EditText
    lateinit var etNewPassword: EditText
    lateinit var etNewBio: EditText
    lateinit var saveBtn: Button
    lateinit var tvAboutMeCurr: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivProfileCurrent = view.findViewById(R.id.ivProfileCurrent)
        tvUsernameCurrent = view.findViewById(R.id.tvUsernameCurrent)
        etNewUsername = view.findViewById(R.id.etNewUsername)
        etNewPassword = view.findViewById(R.id.etNewPassword)
        etNewBio = view.findViewById(R.id.etNewBio)
        saveBtn = view.findViewById(R.id.saveBtn)
        tvAboutMeCurr = view.findViewById(R.id.tvAboutMeCurr)

        val currUser = ParseUser.getCurrentUser()

        saveBtn.setOnClickListener {
            val newUsername = etNewUsername.text.toString()
            val newPassword = etNewPassword.text.toString()
            val newBio = etNewBio.text.toString()

            if(newUsername != "") {
                currUser.username = newUsername
                currUser.setUsername(newUsername)
            }
            if(newPassword != "") {
                currUser.setPassword(newPassword)
            }
            if (newBio != "") {
                currUser.put("aboutMe", newBio)
            }
            currUser.saveInBackground()
        }

        if (currUser != null) {
            tvUsernameCurrent.text = currUser.username

            tvAboutMeCurr.text = currUser.get("aboutMe").toString()

            if(currUser.getParseFile("profilePic")?.url != null) {
                context?.let {
                    Glide.with(it).load(currUser.getParseFile("profilePic")?.url).into(ivProfileCurrent)
                }
            }
        }
    }


}