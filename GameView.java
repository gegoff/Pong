package com.example.gegoff.pong;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private int x = 200;


    public GameView(final Context context) {
        super(context);


        getHolder().addCallback(this);
        OnTouchListener l = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                performClick();
                x= (int)event.getY();
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show() ;
                return false;
            }
        };
        this.setOnTouchListener(l);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void setX(int a){

        x=a;
    }

    public void update(){

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            canvas.drawRect(100, 100, 200, x, paint);
        }
    }

    @Override
    public boolean performClick(){
        Toast.makeText(this.getContext(),"click it",Toast.LENGTH_LONG).show();
        super.performClick();
        return false;
    }


    /*public boolean OnTouchEvent(MotionEvent e){
        x =(int)e.getY();
        return true;
    }
    */
}
