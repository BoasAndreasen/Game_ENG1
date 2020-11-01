package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGame;

public class Block {
    private final int x;
    private final int y;
    private final Rectangle bounds = new Rectangle();

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.bounds.height = (int) (Gdx.graphics.getHeight() / 20);
        this.bounds.width = (int) (Gdx.graphics.getWidth() / 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
