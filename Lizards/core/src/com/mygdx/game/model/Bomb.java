package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Bomb extends Rectangle {
    private float x;
    private float y;
    private final float width;
    private final float height;

    public Bomb(float x, float y){
        this.x = x;
        this.y = y;
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
