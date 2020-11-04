package com.mygdx.game.controller;

import com.mygdx.game.model.Auber;
import com.mygdx.game.model.World;

public class AuberController {
    private World world;
    private Auber auber;

    public AuberController(World world) {
        this.world = world;
        this.auber = world.getAuber();
    }

    private void leftKeyPressed() {
        this.auber.setRotation("left");
        if (auber.getX() >= 50) {
            this.auber.setX(auber.getX() - 10);
        }
    }

    private void leftKeyReleased() {
        //
    }
    //TODO Process Auber key presses
    //TODO Change screen?
    //TODO Collission?
}
