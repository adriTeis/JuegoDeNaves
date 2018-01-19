package com.example.adrianmontes.juegodenaves;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by adrian.montes on 19/01/2018.
 */

public class Ship {
    private int x;
    private int y;
    private int width,height;
    private int velocidadNaveX;
    private GameView gameview;
    private Bitmap bmp;

    public Ship(GameView gameview, Bitmap bmp) {
        this.gameview = gameview;
        this.bmp = bmp;
        //obtenemos el valor de alto y ancho de la clase gameview
        this.width = gameview.getWidth();
        this.height = gameview.getHeight();

        //ahora obtenemos el ancho y el alto de la pantalla
        //lo dividimos entre dos para que la nave aparezca en la mitad de la pantalla
        x = width / 2;
        y=height-bmp.getHeight();
    }


    public void onDraw(Canvas canvas){
//esto es lo basico para poder pintar la imagen
canvas.drawBitmap(bmp,x,y,null);

    }

    public void movLeft() {
        
        if(x > 0){

            x +=-5;

        }
    }

    public void movRight() {
        if(x < height){


            x +=5;

        }
    }
}
