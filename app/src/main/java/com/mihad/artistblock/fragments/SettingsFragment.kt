package com.mihad.artistblock.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
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
import com.parse.ParseFile
import com.parse.ParseUser
import java.nio.ByteBuffer


class SettingsFragment : Fragment() {

    lateinit var ivProfileCurrent: ImageView
    lateinit var tvUsernameCurrent: TextView
    lateinit var etNewUsername: EditText
    lateinit var etNewPassword: EditText
    lateinit var etNewBio: EditText
    lateinit var saveBtn: Button
    lateinit var tvAboutMeCurr: TextView
    lateinit var btnChange: Button

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
        btnChange = view.findViewById(R.id.btnChangeImage)

        val currUser = ParseUser.getCurrentUser()

        btnChange.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivity(intent)
              pickImageGallery()
        }

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
            refresh()
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

    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    private fun refresh(){
        val currUser = ParseUser.getCurrentUser()
        tvUsernameCurrent.text = currUser.username
        tvAboutMeCurr.text = currUser.get("aboutMe").toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){

            ivProfileCurrent.setImageURI(data?.data)

        }

    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK && data != null) {
//            val selectedImageUri : Uri? = data?.data
//            val selectedImage : Bitmap = loadFromUri(selectedImageUri)
//            val ivProfile : ImageView = requireView().findViewById(R.id.ivProfileCurrent)
//            ivProfile.setImageBitmap(selectedImage)
//        }
//    }

//    private fun loadFromUri(photoUri: Uri?): Bitmap {
//        var image: Bitmap? = null
//        try {
//            // check version of Android on device
//            image = if (Build.VERSION.SDK_INT > 27) {
//                // on newer versions of Android, use the new decodeBitmap method
//                val source = ImageDecoder.createSource(
//                    requireActivity().contentResolver,
//                    photoUri!!
//                )
//                ImageDecoder.decodeBitmap(source)
//            } else {
//                // support older versions of Android by using getBitmap
//                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, photoUri)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return image!!
//    }

    companion object {
        const val TAG = "SettingsFragment"
        val IMAGE_REQUEST_CODE = 100
    }
}