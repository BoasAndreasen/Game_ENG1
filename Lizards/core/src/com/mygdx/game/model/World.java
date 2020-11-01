package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class World {
    private Array<Block> blocks = new Array<Block>();
    private CharacterNPC auber;
    private CharacterNPC npc1;
    private CharacterNPC hostile1;
    private CharacterNPC hostile2;
    private CharacterNPC hostile3;

    public World() {
        createWorld();
    }

    private void createWorld() {
        CharacterNPC auber = new CharacterNPC(0, 0, 1, "left");
        blocks.add(new Block(0, 1));
        //TODO
    }
}
