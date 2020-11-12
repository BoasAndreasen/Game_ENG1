package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class HealPad extends Rectangle {

    private final float x;
    private final float y;

    public HealPad(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void Heal(){
        //TODO if clicked then heal auber
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
