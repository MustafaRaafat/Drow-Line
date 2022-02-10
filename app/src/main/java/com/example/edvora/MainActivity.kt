package com.example.edvora

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.ui.DrawingArrow
import com.example.edvora.ui.DrawingLine

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener  {
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 != null) {
            when (p0.getItemAtPosition(p2)){
                "red" -> mPaint.setColor(Color.RED)
                "green"-> mPaint.setColor(Color.RED)
                "black" -> mPaint.setColor(Color.RED)
                "blue" -> mPaint.setColor(Color.RED)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    lateinit var mPaint: Paint
    lateinit var d2: RelativeLayout
    lateinit var pencil_button:RadioButton
    lateinit var arrow_button:RadioButton
    lateinit var rectangle_button:RadioButton
    lateinit var ellipse_button:RadioButton
    lateinit var colorButton:Spinner
    lateinit var radioGroup:RadioGroup
    lateinit var recyclercolor:RecyclerView
    lateinit var readp:ColorAdapter
    var data= arrayOf(Color.BLACK,Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        radioGroup =findViewById(R.id.Radio_container)
        recyclercolor=findViewById(R.id.colors_recy)


        readp=ColorAdapter()
        readp.setcolorData(data)

        recyclercolor.adapter=readp

        pencil_button=findViewById(R.id.draw_line_but)
        arrow_button=findViewById(R.id.draw_arrow_but)
        rectangle_button=findViewById(R.id.draw_rectangle_but)
        ellipse_button=findViewById(R.id.draw_cercle_but)
        colorButton=findViewById(R.id.draw_color_but)
        d2=findViewById(R.id.container_draw)
        ArrayAdapter.createFromResource(this,
        R.array.color_array,
        android.R.layout.simple_spinner_item)
            .also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                colorButton.adapter = arrayAdapter
            }
        colorButton.onItemSelectedListener=this


        mPaint = Paint()

//        d2.onTouchEvent(MotionEvent.obtain(1,1,1,1f,2f,2))
//        d2.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
//
//            return@OnTouchListener true
//        })
//
//        (View.OnTouchListener { view, motionEvent ->
//
//            return@OnTouchListener true
//        })

//        mPaint.setAntiAlias(true);
//        mPaint.setDither(true);
//        mPaint.setColor(readp.getColor());
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeWidth(12f);
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view) {
                 pencil_button->
                    if (checked) {
                        var dv= DrawingLine(this,mPaint,radioGroup,colorButton,
                            recyclercolor)
                        dv.mmPaint.setColor(Color.RED)

                        d2.addView(dv)

                    }

                arrow_button ->
                    if (checked) {
                        var dd=DrawingArrow(this,mPaint,radioGroup,colorButton,recyclercolor)
                        dd.mmPaint.setColor(Color.GREEN)
                        d2.addView(dd)
                    }
                rectangle_button ->
                    if (checked) {
                        // Ninjas rule
                    }
                ellipse_button ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }
}