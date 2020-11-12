package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class Bomb extends Rectangle {

    private final float x;
    private final float y;

    public Bomb(float x,float y){
        this.x=x;
        this.y=y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return x;
    }

}
