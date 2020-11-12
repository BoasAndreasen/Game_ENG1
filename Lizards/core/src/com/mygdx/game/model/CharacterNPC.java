package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class CharacterNPC extends Rectangle {
    private float x;
    private float y;
    private String rotation;
    private final float width;
    private final float height;

    public CharacterNPC(float x, float y, String rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.width = 70;
        this.height = 70;
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

    public String getRotation() {
        return rotation;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int num) {
        this.x = x + num;
    }

    public void addY(int num) {
        this.y = y + num;
    }

    public void setRotation(String rotation) {
        rotation = rotation.toLowerCase();
        if (rotation.equals("left") || rotation.equals("right") || rotation.equals("up") || rotation.equals("down")) {
            this.rotation = rotation;
        } else {
            throw new IllegalArgumentException("Direction must be either left, right, up, down");
        }
    }
}
