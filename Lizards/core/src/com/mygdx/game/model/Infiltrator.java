package com.mygdx.game.model;

public class Infiltrator extends CharacterNPC{
    private final String ability;
    private boolean arrested;
    private boolean current;

    public Infiltrator(float x, float y, String rotation, String ability,boolean arrested, boolean current) {
        super(x, y, rotation);
        this.ability=ability;
        this.arrested=arrested;
        this.current=current;
    }

    public String getAbility() {
        return ability;
    }

    public boolean isArrested() {
        return arrested;
    }

    public boolean isCurrent() {
        return current;
    }
}
