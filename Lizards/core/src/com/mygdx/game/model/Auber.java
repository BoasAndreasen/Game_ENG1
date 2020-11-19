package com.mygdx.game.model;

public class Auber extends CharacterNPC {
    private  int health;

    public Auber(float x, float y, int health) {
        super(x, y);
        this.health=health;
    }

    public int getHealth() {
        return health;
    }

    public void decHealth(int dHealth) {
        if (!((this.health - dHealth) < 0)) {
            this.health = this.health - dHealth;
        }
    }

    public void setHealth(int health){
        this.health = health;
    }
}
