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

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
    
    public boolean notifyPlayer(){ //tells the game screen to render or not
        if (this.health < 100){
            return true;
        }
        else{
            return false;
        }
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = this.health - health;
    }

    public boolean isDestroyed() {
        if (this.health <= 0){
            return true;
        }
        else{
            return false;
        }
    }


}
