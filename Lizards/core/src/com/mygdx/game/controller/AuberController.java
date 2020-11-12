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
        checkTelePadStop();

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
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getX() >= world.getHorizWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getHorizWall().get(i).getX() + world.getHorizWall().get(i).getWidth())
                && (world.getAuber().getY() + world.getAuber().getHeight() + addedY == world.getHorizWall().get(i).getY())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getX() >= world.getVertiWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getVertiWall().get(i).getX() + world.getVertiWall().get(i).getWidth())
                    && (world.getAuber().getY() + world.getAuber().getHeight() + addedY == world.getVertiWall().get(i).getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDownBlockCollission(int addedY) {
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getX() >= world.getHorizWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getHorizWall().get(i).getX() + world.getHorizWall().get(i).getWidth())
                    && (world.getAuber().getY() - addedY - world.getHorizWall().get(i).getHeight() == world.getHorizWall().get(i).getY())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getX() >= world.getVertiWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getVertiWall().get(i).getX() + world.getVertiWall().get(i).getWidth())
                    && (world.getAuber().getY() - addedY - world.getVertiWall().get(i).getHeight() == world.getVertiWall().get(i).getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLeftBlockCollission(int addedX) {
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getY() >= world.getHorizWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getHorizWall().get(i).getY() + world.getHorizWall().get(i).getHeight())
                    && (world.getAuber().getX() - addedX - world.getHorizWall().get(i).getWidth() == world.getHorizWall().get(i).getX())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getY() >= world.getVertiWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getVertiWall().get(i).getY() + world.getVertiWall().get(i).getHeight())
                    && (world.getAuber().getX() - addedX - world.getVertiWall().get(i).getWidth() == world.getVertiWall().get(i).getX())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRightBlockCollission(int addedX) {
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getY() >= world.getHorizWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getHorizWall().get(i).getY() + world.getHorizWall().get(i).getHeight())
                    && (world.getAuber().getX() + addedX + world.getAuber().getWidth() == world.getHorizWall().get(i).getX())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getY() >= world.getVertiWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getVertiWall().get(i).getY() + world.getVertiWall().get(i).getHeight())
                    && (world.getAuber().getX() + addedX + world.getAuber().getWidth() == world.getVertiWall().get(i).getX())) {
                return true;
            }
        }
        return false;
    }

    public void checkTelePadStop() {
        for (int i = 0; i < world.getTelePads().size; i++) {
            if ((world.getAuber().getX() >= world.getTelePads().get(i).getX()) && (world.getAuber().getX() <=
                    world.getTelePads().get(i).getX() + world.getTelePads().get(i).getWidth()) && (
                    (world.getAuber().getY() >= world.getTelePads().get(i).getY()) && (world.getAuber().getY() <=
                            world.getTelePads().get(i).getY() + world.getTelePads().get(i).getHeight()))) {
                world.getAuber().setX(0);
                world.getAuber().setY(0);
            }
        }
    }
}
