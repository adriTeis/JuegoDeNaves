package com.example.adrianmontes.juegodenaves;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by adrian.montes on 19/01/2018.
 */

public class GameView extends SurfaceView {
    private GameLoopView gameloop;
    private SurfaceHolder holder;
    private Ship ship;





    public GameView(Context context) {
        super(context);

        //cada vez que haya un cambio en el cambas va al metodo surface change que es un metodo de la clase holder
        //estos metodos son el metodo canvas que pinta las imagenes en la pantalla
        holder=getHolder();
        gameloop=new GameLoopView(this);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createShip();
                gameloop.setRunning(true);
                gameloop.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry=true;
                gameloop.setRunning(false);
                while(retry){
                    try {
                        gameloop.join();
                        retry=false;


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });


    }
    public Ship createShip(){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.ship);
        ship=new Ship(this,bmp);
        return ship;

    }


    //con este metodo detectamos que parte de la pantalla se toco.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();

        // detectamos si pulsamos entre 0 y 150 que es
        if(x > 0 && x < 150 && y <(getHeight()/2+150) && y > getHeight()/2){
            ship.movLeft();
        }else if(x> getWidth()-150 && x < getWidth() && y <(getHeight()/2+150) && y > getHeight()/2)
        {
            ship.movRight();

        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //pinto un color de fondo
        canvas.drawColor(Color.BLACK);
        //cargo los botones
        Bitmap left=BitmapFactory.decodeResource(getResources(),R.drawable.left);
        Bitmap right=BitmapFactory.decodeResource(getResources(),R.drawable.right);
        //cargo las posiciones de los botones en la pantalla
        canvas.drawBitmap(left,0,getHeight()/2,null);
        canvas.drawBitmap(right,getWidth()-right.getWidth(),getHeight()/2,null);
        //cargo la imagen
        ship.onDraw(canvas);
    }
}
