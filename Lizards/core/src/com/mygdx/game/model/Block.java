package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class Block extends Rectangle {
    private final float x;
    private final float y;
    private final float width;
    private final float height;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
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
        return x;
    }

    @Override
    public float getHeight() {
        return x;
    }
}
