package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class System extends Rectangle {

    private int health;
    private boolean isDestroyed;

    public System(float x,float y, float height,float width, int health,boolean isDestroyed){
        super(x, y, width, height);
        this.health=health;
        this.isDestroyed=isDestroyed;
    }

    public void notifyPlayer(){
        //TODO notify player
    }

    public int getHealth() {
        return health;
        //TODO getter for health
    }

    public void setHealth(int health) {
        this.health = health;
        //TODO set health
    }

    public boolean isDestroyed() {
        return isDestroyed;
        //TODO getter for isDestroyed
    }

    public void setDestroyed() {
        if (this.health==0){
            isDestroyed = true;
        }
        else{
            isDestroyed=false;
        }

        //TODO setter for isDestroyed 

    }
}
