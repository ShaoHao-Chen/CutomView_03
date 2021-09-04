package com.shao.cutomview_03

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import java.util.*

class MyAnime(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val resouces:Resources= context!!.resources
    private var ball:Bitmap = BitmapFactory.decodeResource(resouces, R.drawable.ball);
    private var initFlag:Boolean=false
    private var viewW:Float = 0f
    private var viewH:Float = 0f
    private var ballW:Float = 0f
    private var ballH:Float = 0f
    private var ballX:Float = 0f
    private var ballY:Float = 0f
    private val mTimer:Timer = Timer()

    private var dx=20; private var dy=20

    init {
        setBackgroundColor(Color.WHITE)
        mTimer.schedule(shiftTask(), 1000, 30)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(!initFlag){
            initialize()
            initFlag=true
        }

        canvas?.drawBitmap(ball, ballX, ballY, null)
    }

    private fun initialize() {
        viewW=width.toFloat()
        viewH=height.toFloat()
        ballW=viewW/6
        ballH=ballW

        val mMatrix = Matrix()
        mMatrix.postScale(ballW/ball.width, ballH/ball.height)
        ball = Bitmap.createBitmap(ball,0,0, ball.width, ball.height, mMatrix, false)
    }

    inner class shiftTask:TimerTask(){
        override fun run() {
            if(ballX<0 || (ballX+ballW)>viewW){
                dx *= -1
            }
            if(ballY<0 || (ballY+ballH)>viewH){
                dy *= -1
            }
            ballX += dx
            ballY += dy

            postInvalidate()
        }

    }


}