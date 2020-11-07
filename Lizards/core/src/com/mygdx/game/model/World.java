package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class World {
    private Array<Block> blocks = new Array<Block>();
    private Array<Block> hostiles = new Array<Block>();
    private Auber auber;
    private System sys1;

    public World() {
        createWorld();
    }

    private void createWorld() {
        auber = new Auber(0, 0, 64, 64,1, "left");
        blocks.add(new Block(0, 1, 64, 64));
        sys1= new System(300,300,64,64,100,false);
    }

    public Auber getAuber() {
        return auber;
    }

    public System getSys1(){return sys1;}
}
