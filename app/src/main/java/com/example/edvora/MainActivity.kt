package com.example.edvora

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    lateinit var imageView:ImageView
    lateinit var mPaint: Paint
    lateinit var d2: RelativeLayout


     fun getf():Paint{
         if (mPaint==null){
             mPaint= Paint()
         }
        return mPaint;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPaint = Paint()
        var dv= DrawingView(this,mPaint)
        d2=findViewById(R.id.container_draw);
        d2.addView(dv)
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12f);
    }


    public class DrawingView : View {

        var mywidth: Int = 0
        var myheight: Int = 0
        lateinit var mBitmap: Bitmap
         var mCanvas: Canvas
         var mPath: Path
         var mmPaint:Paint
         var mBitmapPaint: Paint
         var circlePaint: Paint
        lateinit var circlePath: Path
        var mContext : Context
        lateinit var recPaint: Paint
        constructor(context: Context,mPaint: Paint): super(context){
            mPath= Path()
            mCanvas=Canvas()
            mmPaint=mPaint
            circlePath=Path()
            this.mContext=context
            mBitmapPaint=Paint(Paint.DITHER_FLAG)
            circlePaint= Paint()
            circlePaint.setAntiAlias(true);
            circlePaint.setColor(Color.BLUE);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeJoin(Paint.Join.MITER);
            circlePaint.setStrokeWidth(4f);
//            recPaint=new Paint();
//            recPaint.setColor(Color.BLACK);
//            recPaint.setStyle(Paint.Style.STROKE);
//            recPaint.setStrokeJoin(Paint.Join.MITER);
//            recPaint.setStrokeWidth(4f);
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

        private fun touch_start(x:Float, y:Float) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
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
            mPath.lineTo(mX, mY);
            circlePath.reset();
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
                        touch_start(x, y);
                        invalidate();
                    }
                    MotionEvent.ACTION_MOVE->{
                        touch_move(x, y);
                        invalidate();
                    }
                    MotionEvent.ACTION_UP->{
                        touch_up();
                        invalidate();
                    }
                }
            }
            return true;
        }

        /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dv = new DrawingView(this);
        setContentView(R.layout.activity_main);
        d=findViewById(R.id.imageView);
        d2=findViewById(R.id.imagedd);
        ss=findViewById(R.id.sss);
        d2.addView(dv);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    public class DrawingView extends View {


        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath( mPath,  mPaint);
            canvas.drawPath( circlePath,  circlePaint);
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;

//                mCanvas.drawRect(mX,mY,x,y,recPaint);
                circlePath.reset();
                circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            circlePath.reset();
            // commit the path to our offscreen
            mCanvas.drawPath(mPath,  mPaint);
            // kill this so we don't double draw
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }
    *  */

    }
}