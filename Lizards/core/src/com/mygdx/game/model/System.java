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
}
