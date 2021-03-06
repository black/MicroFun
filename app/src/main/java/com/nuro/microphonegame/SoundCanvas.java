package com.nuro.microphonegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
public class SoundCanvas extends View{
    private SoundMeter soundMeter;
    private Context ctx;
    private int r=50;
    private int  xx,yy;
    private int mysecond;
    private int threshold = 190;
    private double prevRad;
    private int score;

    public SoundCanvas(Context context, View ParentView) {
        super(context);
        this.ctx = context;
        RelativeLayout parentView = (RelativeLayout) ParentView;
        xx = parentView.getWidth() / 2;
        yy = parentView.getHeight()/ 2;
        soundMeter = new SoundMeter();
        soundMeter.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(0, 0, 0, 0));
        double rad = soundMeter.getAmplitude();
        if(rad>0){
            rad = map(rad,0,20000,0,250);
            prevRad = rad;
        }else {
            rad = prevRad;
        }

        if(rad>threshold){
            ellipse(canvas,xx,yy,150,Color.RED);
        }else{
            ellipse(canvas,xx,yy,150,Color.GRAY);
        }


//        Log.d("Amp",rad+"");
//        for (int i = 0; i < 360; i += 18) {
//            float x = (float) (xx + (rad + r) * Math.cos(Math.toRadians(i)));
//            float y = (float) (yy + (rad + r) * Math.sin(Math.toRadians(i)));
//            ellipse(canvas, x, y, 6, Color.RED);
//        }
//
//        ring(canvas, xx, yy, threshold, Color.BLACK);
//        ring(canvas, xx, yy, threshold+100, Color.BLACK);

//        if(mysecond<10 && rad>threshold && rad<threshold+100){
//            score++;
//        }

        invalidate();
    }

    public void setTime(long mysecond){
        this.mysecond = (int)mysecond;
    }

    double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    // draw Rectangles
    public void rect(Canvas canvas, float x, float y, float w, float h) {
        Paint rectangle = new Paint();
        rectangle.setAntiAlias(true);
        rectangle.setStyle(Paint.Style.STROKE);
        rectangle.setColor(Color.GRAY);
        RectF _rect = new RectF(x, y, x + w, y + h);
        canvas.drawRect(_rect, rectangle);
    }

    // draw Circles
    public void ellipse(Canvas canvas, float x, float y, float r, int color) {
        Paint ellipse = new Paint();
        ellipse.setAntiAlias(true);
        ellipse.setStyle(Paint.Style.FILL);
        ellipse.setColor(color);
        RectF circle = new RectF(x - r, y - r, x + r, y + r);
        canvas.drawOval(circle, ellipse);
    }

    // draw Circles
    public void ring(Canvas canvas, float x, float y, float r, int color) {
        Paint ring = new Paint();
        ring.setStyle(Paint.Style.STROKE);
        ring.setColor(color);
        ring.setStrokeWidth(1);
        ring.setAntiAlias(true);
        RectF circle = new RectF(x - r, y - r, x + r, y + r);
        canvas.drawOval(circle, ring);
    }

    // draw Lines
    public void line(Canvas canvas, float x, float y, float xx, float yy) {
        Paint line = new Paint();
        line.setAntiAlias(true);
        line.setStrokeWidth(5);
        line.setStyle(Paint.Style.STROKE);
        line.setColor(Color.WHITE);
        canvas.drawLine(x, y, xx, yy, line);
    }

    // draw text
    public void label(Canvas canvas, float x, float y, String msg) {
        Paint label = new Paint();
        label.setAntiAlias(true);
        label.setColor(Color.BLACK);
        label.setTextSize(20);
        canvas.drawText(msg, x, y, label);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

}