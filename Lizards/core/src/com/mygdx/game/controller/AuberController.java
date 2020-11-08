package com.mygdx.game.controller;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.model.Auber;
import com.mygdx.game.model.World;
import com.mygdx.game.screens.GameScreen;

public class AuberController implements InputProcessor {
    private World world;
    private Auber auber;
    private GameScreen gameScreen;
    private boolean leftPressed, rightPressed, downPressed, upPressed;

    public AuberController(World world) {
        this.world = world;
        this.auber = world.getAuber();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 19 || keycode == 51) {
            upPressed = true;
        }
        else if (keycode == 20 || keycode == 47) {
            downPressed = true;
        }
        else if (keycode == 21 || keycode == 29) {
            leftPressed = true;
        }
        else if (keycode == 22 || keycode == 32) {
           rightPressed = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 19 || keycode == 51) {
            upPressed = false;
        }
        else if (keycode == 20 || keycode == 47) {
            downPressed = false;
        }
        else if (keycode == 21 || keycode == 29) {
            leftPressed = false;
        }
        else if (keycode == 22 || keycode == 32) {
            rightPressed = false;
        }

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

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    //TODO Change screen?
    //TODO Collission?
}
