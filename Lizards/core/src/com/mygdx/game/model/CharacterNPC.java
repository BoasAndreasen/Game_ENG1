package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class CharacterNPC {
    private int x;
    private int y;
    private int speed;
    private String rotation;
    private Rectangle bounds = new Rectangle();

    public CharacterNPC(int x, int y, int speed, String rotation) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.rotation = rotation;
        this.bounds.height = (int) (Gdx.graphics.getHeight() / 20);
        this.bounds.width = (int) (Gdx.graphics.getWidth() / 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public String getRotation() {
        return rotation;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
