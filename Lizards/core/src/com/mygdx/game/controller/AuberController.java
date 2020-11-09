package com.mygdx.game.controller;

	
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.model.Auber;
import com.mygdx.game.model.World;

public class AuberController implements InputProcessor {
    private World world;
    private Auber auber;
    private boolean leftPressed, rightPressed, downPressed, upPressed;

    public AuberController(World world) {
        this.world = world;
        this.auber = world.getAuber();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 19 || keycode == 51) {
            upPressed = true;
        } else if (keycode == 20 || keycode == 47) {
            downPressed = true;
        } else if (keycode == 21 || keycode == 29) {
            leftPressed = true;
        } else if (keycode == 22 || keycode == 32) {
            rightPressed = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 19 || keycode == 51) {
            upPressed = false;
        } else if (keycode == 20 || keycode == 47) {
            downPressed = false;
        } else if (keycode == 21 || keycode == 29) {
            leftPressed = false;
        } else if (keycode == 22 || keycode == 32) {
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

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean checkUpBlockCollission(int addedY) {
        for (int i = 0; i < world.getBlocks().size; i++) {
            if (world.getAuber().getX() <= world.getBlocks().get(i).getX() &&
                world.getAuber().getX() + world.getAuber().getWidth() >= world.getBlocks().get(i).getX()
                && world.getAuber().getY() + world.getAuber().getHeight() + addedY == world.getBlocks().get(i).getY()) {
                System.out.println("COLLIDED");
                return true;
            }
        }
        return false;
    }
}
