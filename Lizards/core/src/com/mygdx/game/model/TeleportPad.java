package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class TeleportPad extends Rectangle {

    private final float x;
    private final float y;

    public TeleportPad (float x, float y){
        this.x=x;
        this.y=y;
    }

    public void TeleLocations(){
        //TODO when clicked gives list of tele locations
    }

    public void Teleport(){
        //TODO teleports you to another room
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
