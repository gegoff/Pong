package com.example.gegoff.pong;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.widget.Toast;

public class MainThread extends Thread {

    private final SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;


    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }
    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    //this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
                Toast.makeText(gameView.getContext(), "catchin stuff", Toast.LENGTH_LONG).show();}
            finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void setRunning(boolean isRunning) {
        running = isRunning;
    }



}
