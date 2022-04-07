package com.mihad.artistblock.fragments

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.mihad.artistblock.PaintView.Companion.colorList
import com.mihad.artistblock.PaintView.Companion.currentBrush
import com.mihad.artistblock.PaintView.Companion.pathList
import com.mihad.artistblock.R


class DrawingFragment : Fragment() {

    companion object {

        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_drawing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inflate the layout for this fragment
        val whiteBtn = view.findViewById<ImageButton>(R.id.whiteBtn)
        val blackBtn = view.findViewById<ImageButton>(R.id.blackBtn)
        val redBtn = view.findViewById<ImageButton>(R.id.redBtn)
        val greenBtn = view.findViewById<ImageButton>(R.id.greenBtn)
        val blueBtn = view.findViewById<ImageButton>(R.id.blueBtn)

        val uploadBtn = view.findViewById<ImageButton>(R.id.uploadBtn)

        whiteBtn.setOnClickListener {
            paintBrush.color = Color.WHITE //erase
            currentColor(paintBrush.color)
            pathList.clear()
            colorList.clear()
            path.reset()
        }

        blackBtn.setOnClickListener {
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
        }

        redBtn.setOnClickListener {
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }

        greenBtn.setOnClickListener {
            paintBrush.color = Color.GREEN
            currentColor(paintBrush.color)
        }

        blueBtn.setOnClickListener {
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }

        uploadBtn.setOnClickListener {
            val map = view.findViewById<RelativeLayout>(R.id.pvCanvas)
            val bitmap = map.drawToBitmap()


        }

    }

    private fun goToPost() {


    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }


}