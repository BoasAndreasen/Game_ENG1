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

    public boolean closeToHostile(Infiltrator hostile){
        if (((this.getX() > hostile.getX()) && (this.getX() < hostile.getX() + 400) && ((this.getY() > hostile.getY()) && (this.getY() < hostile.getY() + 400))) ||
                ((this.getX() < hostile.getX()) && (this.getX() > hostile.getX() - 400) && (this.getY() > hostile.getY()) && (this.getY() < hostile.getY() + 400)) ||
                ((this.getX() > hostile.getX()) && (this.getX() < hostile.getX() + 400) && ((this.getY() < hostile.getY()) && (this.getY() > hostile.getY() - 400))) ||
                ((this.getX() < hostile.getX()) && (this.getX() > hostile.getX() - 400) && (this.getY() < hostile.getY()) && (this.getY() > hostile.getY() - 400))) {
            return true;
        }
        else{return false;}
    }

    public boolean closeToSystem(System sys){
        if (((this.getX() > sys.getX()) && (this.getX() < sys.getX() + 400) && ((this.getY() > sys.getY()) && (this.getY() < sys.getY() + 400))) ||
                ((this.getX() < sys.getX()) && (this.getX() > sys.getX() - 400) && (this.getY() > sys.getY()) && (this.getY() < sys.getY() + 400)) ||
                ((this.getX() > sys.getX()) && (this.getX() < sys.getX() + 400) && ((this.getY() < sys.getY()) && (this.getY() > sys.getY() - 400))) ||
                ((this.getX() < sys.getX()) && (this.getX() > sys.getX() - 400) && (this.getY() < sys.getY()) && (this.getY() > sys.getY() - 400))) {
            return true;
        }
        else{return false;}
    }

    public void arrest() {

    }
}
