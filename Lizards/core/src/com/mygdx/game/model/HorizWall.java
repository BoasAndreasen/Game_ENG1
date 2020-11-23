package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class HorizWall extends Rectangle {
    private final float x;
    private final float y;
    private final float width;
    private final float height;

    public HorizWall(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 10;
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
}