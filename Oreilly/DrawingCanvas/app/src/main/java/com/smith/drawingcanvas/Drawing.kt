package com.smith.drawingcanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Drawing : View {

    private val brush = Paint(Paint.ANTI_ALIAS_FLAG)
    private val redBrush = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linearGradient =
        LinearGradient(0f, 0f, 200f, 200f, Color.RED, Color.BLACK, Shader.TileMode.MIRROR)
    private val radialGradient =
        RadialGradient(0f, 0f, 20f, Color.GREEN, Color.BLUE, Shader.TileMode.MIRROR)

    //  private val sweepGradient: LinearGradient()
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img)


    constructor(context: Context) : super(context) {}

//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {}
//
//    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
//    }
//
//    constructor(context: Context, attrs: AttributeSet, defStyle: Int, defStyleRes: Int) : super(context, attrs, defStyle, defStyleRes) {
//    }

    init {

        brush.apply {
            color = Color.parseColor("green")
            shader = radialGradient
        }
        redBrush.apply {
            color = Color.parseColor("red")
            strokeWidth = 8f
        }


    }

    override fun onDraw(canvas: Canvas?) {

        canvas?.apply {


//            drawCircle(measuredWidth.toFloat() / 2, measuredHeight.toFloat() / 2, 300f, brush)
//            drawLine(0f, 0f, measuredWidth.toFloat()/2, measuredHeight.toFloat()/2, redBrush)
            drawBitmap(bitmap,2f, 2f, null)
            save()
        }
        super.onDraw(canvas)

    }

}