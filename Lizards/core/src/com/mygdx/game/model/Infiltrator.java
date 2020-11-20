package com.mygdx.game.model;

public class Infiltrator extends CharacterNPC{
    private final String ability;
    private boolean arrested;
    private boolean current;
    private boolean shieldUp;

    public Infiltrator(float x, float y, String ability, boolean arrested, boolean current, boolean shieldUp) {
        super(x, y);
        this.ability = ability;
        this.arrested = arrested;
        this.current = current;
        this.shieldUp = shieldUp;
    }

    public String getAbility() {
        return ability;
    }

    public boolean isArrested() {
        return arrested;
    }

    public boolean getShieldUp(){
        return shieldUp;
    }

    public void setShieldUp(boolean shieldUp) {
        this.shieldUp = shieldUp;
    }

    public void setArrested(boolean newArrested) {
        this.arrested = newArrested;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean newCurrent) {
        this.current = newCurrent;
    }
}
