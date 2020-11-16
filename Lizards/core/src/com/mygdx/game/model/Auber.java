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

    public void decHealth(int health) {
        this.health = this.health-health;
    }

    public void setHealth(int health){
        this.health=health;
    }

    public boolean closeToInfiltrator(Infiltrator infiltrator){
        if (((this.getX() > infiltrator.getX()) && (this.getX() < infiltrator.getX() + 400) && ((this.getY() > infiltrator.getY()) && (this.getY() < infiltrator.getY() + 400))) ||
                ((this.getX() < infiltrator.getX()) && (this.getX() > infiltrator.getX() - 400) && (this.getY() > infiltrator.getY()) && (this.getY() < infiltrator.getY() + 400)) ||
                ((this.getX() > infiltrator.getX()) && (this.getX() < infiltrator.getX() + 400) && ((this.getY() < infiltrator.getY()) && (this.getY() > infiltrator.getY() - 400))) ||
                ((this.getX() < infiltrator.getX()) && (this.getX() > infiltrator.getX() - 400) && (this.getY() < infiltrator.getY()) && (this.getY() > infiltrator.getY() - 400))) {
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
