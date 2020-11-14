package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Bomb extends Rectangle {
    private final float width;
    private final float height;

    public Bomb(){
        this.width = 100;
        this.height = 100;
    }



    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public int randBomb(){ //
        int min=0;
        int max=1;
        Random rand= new Random();
        int n= rand.nextInt(2);
        return n;
    } //randomly chooses 0 or 1


}
