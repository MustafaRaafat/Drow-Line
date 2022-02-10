package com.example.edvora.ui

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.ColorAdapter

class DrawingArrow : View {


    var mywidth: Int = 0
    var myheight: Int = 0
    lateinit var mBitmap: Bitmap
    var mCanvas: Canvas
    var mPath: Path
    var mmPaint: Paint
    var radioGroup: RadioGroup
    var colorButton: Spinner
    var colorWheel: RecyclerView
    var mBitmapPaint: Paint
    var circlePaint: Paint
    lateinit var circlePath: Path
    var mContext : Context
    lateinit var recPaint: Paint



    constructor(context: Context, mPaint: Paint, radioGroup: RadioGroup,
                colorButton: Spinner, colorel: RecyclerView)
            : super(context){
        mPath= Path()
        mCanvas= Canvas()
        mmPaint=mPaint
        mmPaint.setAntiAlias(true)
        mmPaint.setDither(true)
        mmPaint.setStyle(Paint.Style.STROKE);
        mmPaint.setStrokeJoin(Paint.Join.ROUND);
        mmPaint.setStrokeCap(Paint.Cap.ROUND);
        mmPaint.setStrokeWidth(12f);
        this.colorButton=colorButton
        this.colorWheel=colorel
        this.radioGroup=radioGroup
        circlePath= Path()
        this.mContext=context
        mBitmapPaint= Paint(Paint.DITHER_FLAG)
        circlePaint= Paint()
        circlePaint.setAntiAlias(true)
        circlePaint.setColor(Color.RED)
        circlePaint.setStyle(Paint.Style.STROKE)
        circlePaint.setStrokeJoin(Paint.Join.MITER)
        circlePaint.setStrokeWidth(4f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = Canvas(mBitmap);
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(mBitmap,0f,0f,mBitmapPaint)
        canvas.drawPath( mPath, mmPaint )
        canvas.drawPath( circlePath,  circlePaint)
    }


    private  var mX=0f
    private  var mY=0f

    val TOUCH_TOLERANCE = 4f;

    private fun touch_start(x:Float, y:Float, color:Int) {
        mPath.reset()
        mPath.moveTo(x, y)
        mX = x
        mY = y
        mmPaint.setColor(color)
    }

    private fun touch_move( x:Float, y:Float) {
        var dx = Math.abs(x - mX);
        var dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;

//                mCanvas.drawRect(mX,mY,x,y,recPaint);
            circlePath.reset();
            circlePath.addCircle(mX, mY, 30f, Path.Direction.CW);
        }
    }

    private fun touch_up() {
        mPath.lineTo(mX, mY)
        mPath.lineTo(mX,mY)
        circlePath.reset()
        // commit the path to our offscreen
        mCanvas.drawPath(mPath,  mmPaint)
        // kill this so we don't double draw
        mPath.reset()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x=0f
        var y=0f
        if (event != null) {
            x = event.getX()
            y = event.getY()

            when (event.getAction()) {
                MotionEvent.ACTION_DOWN->{
                    radioGroup.visibility=GONE
                    colorWheel.visibility=GONE
                    colorButton.visibility=GONE
                    touch_start(x, y,Color.BLACK)
                    invalidate()
                }
                MotionEvent.ACTION_MOVE->{
                    colorWheel.visibility= GONE
                    radioGroup.visibility= GONE
                    colorButton.visibility= GONE
                    touch_move(x, y);
                    invalidate();
                }
                MotionEvent.ACTION_UP->{
                    colorWheel.visibility= GONE
                    radioGroup.visibility= VISIBLE
                    colorButton.visibility=VISIBLE
                    touch_up();
                    invalidate();
                }
            }
        }
        return true;
    }
}