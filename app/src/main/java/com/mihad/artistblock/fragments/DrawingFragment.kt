package com.mihad.artistblock.fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.mihad.artistblock.PaintView.Companion.colorList
import com.mihad.artistblock.PaintView.Companion.currentBrush
import com.mihad.artistblock.PaintView.Companion.pathList
import com.mihad.artistblock.Post
import com.mihad.artistblock.R
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.ByteArrayOutputStream


class DrawingFragment : Fragment() {

    companion object {

        var path = Path()
        var paintBrush = Paint()

        const val TAG = "DrawingFragment"
    }

    lateinit var bitmap : Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_drawing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inflate the layout for this fragment
        val clearBtn = view.findViewById<ImageButton>(R.id.clearBtn)
        val eraseBtn = view.findViewById<ImageButton>(R.id.eraseBtn)
        val blackBtn = view.findViewById<ImageButton>(R.id.blackBtn)
        val redBtn = view.findViewById<ImageButton>(R.id.redBtn)
        val greenBtn = view.findViewById<ImageButton>(R.id.greenBtn)
        val blueBtn = view.findViewById<ImageButton>(R.id.blueBtn)
        val yellowBtn = view.findViewById<ImageButton>(R.id.yellowBtn)
        val purpleBtn  =view.findViewById<ImageButton>(R.id.purpleBtn)
        val orangeBtn = view.findViewById<ImageButton>(R.id.orangeBtn)

        val uploadBtn = view.findViewById<Button>(R.id.uploadBtn)

        clearBtn.setOnClickListener {
            paintBrush.color = Color.WHITE //erase
            currentColor(paintBrush.color)
            pathList.clear()
            colorList.clear()
            path.reset()
        }

        eraseBtn.setOnClickListener {
            paintBrush.color = Color.WHITE
            currentColor(paintBrush.color)
        }

        blackBtn.setOnClickListener {
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
        }

        redBtn.setOnClickListener {
            paintBrush.color = Color.rgb(244, 67, 54)
            currentColor(paintBrush.color)
        }

        greenBtn.setOnClickListener {
            paintBrush.color = Color.rgb(76, 175, 80)
            currentColor(paintBrush.color)
        }

        blueBtn.setOnClickListener {
            paintBrush.color = Color.rgb(33, 150, 243)
            currentColor(paintBrush.color)
        }

        yellowBtn.setOnClickListener {
            paintBrush.color = Color.rgb(239, 215, 123)
            currentColor(paintBrush.color)
        }

        orangeBtn.setOnClickListener {
            paintBrush.color = Color.rgb(255, 98, 0)
            currentColor(paintBrush.color)
        }

        purpleBtn.setOnClickListener {
            paintBrush.color = Color.rgb(78,17,88)
            currentColor(paintBrush.color)
        }

        uploadBtn.setOnClickListener {
            val post = Post()

            val map = view.findViewById<RelativeLayout>(R.id.pvCanvas)
            bitmap = map.drawToBitmap()

            val imageView = view.findViewById<ImageView>(R.id.ivDrawing)
            imageView.setImageBitmap(bitmap)

            // Convert it to byte
            // Convert it to byte
            val stream = ByteArrayOutputStream()
            // Compress image to lower quality scale 1 - 100
            // Compress image to lower quality scale 1 - 100
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image: ByteArray = stream.toByteArray()

            // Create the ParseFile

            // Create the ParseFile
            val file = ParseFile("androidbegin.png", image)

            val etCaption = view.findViewById<EditText>(R.id.etCaption)
            post.setDescription(etCaption.text.toString())
            post.setImage(file)
            post.setUser(ParseUser.getCurrentUser())
            post.saveInBackground()

            pathList.clear()
            colorList.clear()
            path.reset()
        }

    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }


}