package com.mihad.artistblock

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

lateinit var ivBitmap: Bitmap

class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        ivBitmap = intent.getParcelableExtra("ivBitmap")!!

        val idBitPic = findViewById<ImageView>(R.id.idBitPic)

        idBitPic.setImageBitmap(ivBitmap)
    }

}