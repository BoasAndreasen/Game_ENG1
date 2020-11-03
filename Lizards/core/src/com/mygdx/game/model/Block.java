package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class Block extends Rectangle {
    private float x;
    private float y;
    private final float width;
    private final float height;

    public Block(int x, int y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return x;
    }

    @Override
    public float getWidth() {
        return x;
    }

    @Override
    public float getHeight() {
        return x;
    }
}
