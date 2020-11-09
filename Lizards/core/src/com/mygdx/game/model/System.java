package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class System extends Rectangle {

    private final float x;
    private final float y;
    private final float height;
    private final float width;
    private float health;
    private boolean isDestroyed;


    public System(float x, float y, float health, boolean isDestroyed){
        this.x = x;
        this.y = y;
        this.health = health;
        this.isDestroyed = isDestroyed;
        this.width = 100;
        this.height = 100;
    }

    public void notifyPlayer(){
        //TODO notify player
        // if hostile attacking system (decrement in health?) then notify player
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed() {
        if (this.health == 0){
            isDestroyed = true;
        }
        else {
            isDestroyed = false;
        }

    }
}
