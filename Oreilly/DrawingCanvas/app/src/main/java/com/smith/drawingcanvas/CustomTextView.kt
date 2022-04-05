package com.smith.drawingcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)


    init {
        paint.apply {
            color = Color.parseColor("blue")
            textSize = 125f
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawText(
                "Hello World",
                measuredWidth.toFloat() / 2,
                measuredHeight.toFloat() / 2,
                paint
            )

            save()
        }
        super.onDraw(canvas)
    }
}