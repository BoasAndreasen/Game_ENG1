package com.mygdx.game.model;

public class Infiltrator extends CharacterNPC{
    private final String ability;
    private boolean arrested;

    public Infiltrator(float x, float y, String rotation, String ability,boolean arrested) {
        super(x, y, rotation);
        this.ability=ability;
        this.arrested=arrested;
    }

    public String getAbility() {
        return ability;
    }

    public boolean isArrested() {
        return arrested;
    }


}
