package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class System extends Rectangle {

    private final float x;
    private final float y;
    private final float height;
    private final float width;
    private float health;

    public System(float x, float y, float health){
        this.x = x;
        this.y = y;
        this.health = health;
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

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
    
    public boolean notifyPlayer(){ //tells the game screen to render or not
        return this.health < 100;
    }

    public float getHealth() {
        return health;
    }

    public void decHealth(int health) {
        this.health = this.health - health;
    }

    public boolean isDestroyed() {
        return this.health <= 0;
    }
}
