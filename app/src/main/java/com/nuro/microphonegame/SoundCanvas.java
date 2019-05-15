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
    private long r=50,rad;
    private int  xx,yy;
    private long mysecond;

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
        Log.d("Amp",soundMeter.getAmplitude()+"");
        rad = (int)soundMeter.getAmplitude();
        rad = (int) map(rad,0,20000,0,150);
        for(int i=0;i<360;i+=18) {
            float x = (float) (xx + (rad+r) * Math.cos(Math.toRadians(i)));
            float y = (float) (yy + (rad+r) * Math.sin(Math.toRadians(i)));
            ellipse(canvas,x,y,8,Color.RED);
        }

        invalidate();
    }

    public void setTime(long mysecond){
        this.mysecond = mysecond;
    }

    long map(long x, long in_min, long in_max, long out_min, long out_max) {
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
        ring.setStrokeWidth(5);
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