package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class CharacterNPC extends Rectangle {
    private float x;
    private float y;
    private final float width;
    private final float height;
    private int speed;
    private String rotation;

    public CharacterNPC(float x, float y, float width, float height, int speed, String rotation) {
        this.x = x;
        this.y = y;
        this.width = (float) (Gdx.graphics.getWidth() / 20);
        this.height = (float) (Gdx.graphics.getHeight() / 20);
        this.speed = speed;
        this.rotation = rotation;
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

    public int getSpeed() {
        return speed;
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

    public void setSpeed(int speed) {
        this.speed = speed;
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
