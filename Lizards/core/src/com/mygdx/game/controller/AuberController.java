package com.mygdx.game.controller;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.model.Auber;
import com.mygdx.game.model.World;

public class AuberController implements InputProcessor {
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

    @Override
    public boolean keyDown(int keycode) {
        System.out.println(keycode);
        // if left then controller.leftKeyPressed....
        // TODO Move to controller
        // TODO Set to true or false
        if (keycode == 19) {
            world.getAuber().addY(20);
        } else if (keycode == 20) {
            world.getAuber().addY(-20);
        } else if (keycode == 21) {
            //leftKeyPressed()..
            world.getAuber().addX(-20);
        } else if (keycode == 22) {
            world.getAuber().addX(20);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    //TODO Process Auber key presses
    //TODO Change screen?
    //TODO Collission?
}
