package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class World {
    private Array<Block> blocks = new Array<Block>();
    private Array<Block> hostiles = new Array<Block>();
    private Auber auber;

    public World() {
        createWorld();
    }

    private void createWorld() {
        auber = new Auber(0, 0, 64, 64,1, "left");
        blocks.add(new Block(0, 1, 64, 64));
    }

    public Auber getAuber() {
        return auber;
    }
}
