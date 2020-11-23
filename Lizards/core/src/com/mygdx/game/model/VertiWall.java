package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class VertiWall extends Rectangle {
    private final float x; //coordinate X
    private final float y; //coordinate Y
    private final float width;
    private final float height;

    public VertiWall(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 10;
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
}