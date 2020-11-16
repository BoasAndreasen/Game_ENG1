package com.mygdx.game.model;

public class Infiltrator extends CharacterNPC{
    private final String ability;
    private boolean arrested;
    private boolean current;

    public Infiltrator(float x, float y, String ability, boolean arrested, boolean current) {
        super(x, y);
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

    public void setCurrent(boolean x) {
        this.current = x;
    }
}
