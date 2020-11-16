package com.mygdx.game.model;

public class Auber extends CharacterNPC {

    private  int health;

    public Auber(float x, float y, String rotation, int health) {
        super(x, y, rotation);
        this.health=health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = this.health-health;
    }

    public void arrest() {

    }
}
