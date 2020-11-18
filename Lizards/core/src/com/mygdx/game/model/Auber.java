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

    public boolean closeToInfiltrator(float Ix, float Iy){
        if (((this.getX() > Ix) && (this.getX() < Ix + 200) &&
                ((this.getY() > Iy) && (this.getY() < Iy + 200))) ||
                (((this.getX() < Ix)) && (this.getX() > Ix - 200) &&
                        (this.getY() > Iy) && (this.getY() < Iy + 200)) ||
                ((this.getX() > Ix) && (this.getX() < Ix + 200) &&
                        ((this.getY() < Iy) && (this.getY() > Iy - 200))) ||
                ((this.getX() < Ix) && (this.getX() > Ix - 200) &&
                        (this.getY() < Iy) && (this.getY() > Iy - 200))) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean closeToSystem(System sys){
        if (((this.getX() > sys.getX()) && (this.getX() < sys.getX() + 400) && ((this.getY() > sys.getY()) &&
                (this.getY() < sys.getY() + 400))) || ((this.getX() < sys.getX()) && (this.getX() > sys.getX() - 400)&&
                (this.getY() > sys.getY()) && (this.getY() < sys.getY() + 400)) ||
                ((this.getX() > sys.getX()) && (this.getX() < sys.getX() + 400) &&
                        ((this.getY() < sys.getY()) && (this.getY() > sys.getY() - 400))) ||
                ((this.getX() < sys.getX()) && (this.getX() > sys.getX() - 400) &&
                        (this.getY() < sys.getY()) && (this.getY() > sys.getY() - 400))) {
            return true;
        }
        else{
            return false;
        }
    }


}
