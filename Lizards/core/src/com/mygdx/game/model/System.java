package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class System extends Rectangle {

    private float health;
    private boolean isDestroyed;

    public System(float x,float y, float height,float width, float health,boolean isDestroyed){
        super(x, y, width, height);
        this.health=health;
        this.isDestroyed=isDestroyed;
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
        if (this.health==0){
            isDestroyed = true;
        }
        else {
            isDestroyed = false;
        }

    }
}
