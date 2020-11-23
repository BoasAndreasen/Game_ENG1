package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class CharacterNPC extends Rectangle {
    private float x; //coordinate X
    private float y; //coordinate Y
    private final float width;
    private final float height;

    public CharacterNPC(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
    }

    //Check if character close to the system
    public boolean closeToSystem(System sys){
        return (this.getX() >= sys.getX() - 100) && (this.getX() <= sys.getX() + 100) &&
                (this.getY() >= sys.getY() - 100) && (this.getY() <= sys.getY() + 100);
    }

    //Check if one character is close to another
    public boolean closeToCharacterNPC(CharacterNPC characterNPC){
        return (this.getX() >= characterNPC.getX() - 200) && (this.getX() <= characterNPC.getX() + 200) &&
                (this.getY() >= characterNPC.getY() - 200) && (this.getY() <= characterNPC.getY() + 200);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int num) {
        this.x = x + num;
    }

    public void addY(int num) {
        this.y = y + num;
    }
}
