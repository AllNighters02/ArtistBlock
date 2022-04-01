package com.mihad.artistblock

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.File

@ParseClassName("User")
class User : ParseUser() {

    fun getProfilePic(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun setProfilePic(profilePic: ParseFile) {
        put(KEY_IMAGE, profilePic)
    }

    companion object {
        const val KEY_IMAGE = "profilePic"
    }
}
