package com.mygdx.game.controller;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.model.Auber;
import com.mygdx.game.model.World;

public class AuberController implements InputProcessor {
    private World world;
    private Auber auber;
    private boolean standingOnTelePad;
    private boolean standingOnHealPad;
    private boolean leftPressed, rightPressed, downPressed, upPressed;

    public AuberController(World world) {
        this.world = world;
        this.auber = world.getAuber();
    }

    @Override
    public boolean keyDown(int keycode) {
        standingOnTelePad = false;
        standingOnHealPad = false;

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
        checkHealPadStop();

        if (standingOnHealPad) {
            world.getAuber().setHealth(100);
        }

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
        if (standingOnTelePad) {
            if (screenX >= 200 && screenX <= 400 && screenY >= 140 && screenY <= 220) {
                // Armory
                world.getAuber().setX((int) world.getTelePads().get(0).getX() + 20);
                world.getAuber().setY((int) world.getTelePads().get(0).getY() + 20);
                standingOnTelePad = false;
            } else if (screenX >= 404 && screenX <= 600 && screenY >= 144 && screenY <= 300) {
                // Contorl room
                world.getAuber().setX((int) world.getTelePads().get(1).getX() + 10);
                world.getAuber().setY((int) world.getTelePads().get(1).getY() + 20);
                standingOnTelePad = false;
            } else if (screenX >= 604 && screenX <= 760 && screenY >= 140 && screenY <= 300) {
                // Cantine
                world.getAuber().setX((int) world.getTelePads().get(2).getX() + 20);
                world.getAuber().setY((int) world.getTelePads().get(2).getY() + 20);
                standingOnTelePad = false;
            } else if (screenX >= 840 && screenX <= 1080 && screenY >= 220 && screenY <= 300) {
                // Brig
                world.getAuber().setX((int) world.getTelePads().get(3).getX() + 20);
                world.getAuber().setY((int) world.getTelePads().get(3).getY() + 20);
                standingOnTelePad = false;
            } else if (screenX >= 200 && screenX <= 400 && screenY >= 304 && screenY <= 460) {
                // Engines
                world.getAuber().setX((int) world.getTelePads().get(4).getX() + 20);
                world.getAuber().setY((int) world.getTelePads().get(4).getY() + 20);
                standingOnTelePad = false;
            } else if (screenX >= 484 && screenX <= 600 && screenY >= 304 && screenY <= 460) {
                // Infirmary
                world.getAuber().setX((int) world.getTelePads().get(5).getX() + 10);
                world.getAuber().setY((int) world.getTelePads().get(5).getY() + 10);
                standingOnTelePad = false;
            } else if (screenX >= 604 && screenX <= 760 && screenY >= 304 && screenY <= 460) {
                // Reactor
                world.getAuber().setX((int) world.getTelePads().get(6).getX() + 20);
                world.getAuber().setY((int) world.getTelePads().get(6).getY() + 10);
                standingOnTelePad = false;
            } else if (screenX >= 840 && screenX <= 1080 && screenY >= 304 && screenY <= 460) {
                // Cargo Bay
                world.getAuber().setX((int) world.getTelePads().get(7).getX() + 20);
                world.getAuber().setY((int) world.getTelePads().get(7).getY() + 20);
                standingOnTelePad = false;
            } else if (screenX <= 120 || screenX >= 1080 || screenY <= 60 || screenY >= 540) {
                // Outside map
                standingOnTelePad = false;
            }
        }
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

    public void updateAuberLocation() {
        if (isUpPressed()) {
            if (!(world.getAuber().getY() >= 1130)) {
                if (!(checkUpBlockCollission(10))) {
                    world.getAuber().addY(10);
                }
            }
        } else if (isDownPressed()) {
            if (!(world.getAuber().getY() <= 0)) {
                if (!(checkDownBlockCollission(10))) {
                    world.getAuber().addY(-10);
                }
            }
        } else if (isLeftPressed()) {
            if (!(world.getAuber().getX() <= 0)) {
                if (!(checkLeftBlockCollission(10))) {
                    world.getAuber().addX(-10);
                }
            }
        } else if (isRightPressed()) {
            if (!(world.getAuber().getX() >= 2330)) {
                if (!(checkRightBlockCollission(10))) {
                    world.getAuber().addX(10);
                }
            }
        }
    }

    public boolean checkUpBlockCollission(int addedY) {
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getX() >= world.getHorizWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getHorizWall().get(i).getX() +
                            world.getHorizWall().get(i).getWidth())
                && (world.getAuber().getY() + world.getAuber().getHeight() + addedY ==
                    world.getHorizWall().get(i).getY())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getX() >= world.getVertiWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getVertiWall().get(i).getX() +
                            world.getVertiWall().get(i).getWidth())
                    && (world.getAuber().getY() + world.getAuber().getHeight() + addedY ==
                        world.getVertiWall().get(i).getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDownBlockCollission(int addedY) {
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getX() >= world.getHorizWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getHorizWall().get(i).getX() +
                            world.getHorizWall().get(i).getWidth())
                    && (world.getAuber().getY() - addedY - world.getHorizWall().get(i).getHeight() ==
                        world.getHorizWall().get(i).getY())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getX() >= world.getVertiWall().get(i).getX() - world.getAuber().getWidth()) &&
                    (world.getAuber().getX() <= world.getVertiWall().get(i).getX() +
                            world.getVertiWall().get(i).getWidth())
                    && (world.getAuber().getY() - addedY - world.getVertiWall().get(i).getHeight() ==
                        world.getVertiWall().get(i).getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLeftBlockCollission(int addedX) {
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getY() >= world.getHorizWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getHorizWall().get(i).getY() +
                            world.getHorizWall().get(i).getHeight())
                    && (world.getAuber().getX() - addedX - world.getHorizWall().get(i).getWidth() ==
                        world.getHorizWall().get(i).getX())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getY() >= world.getVertiWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getVertiWall().get(i).getY() +
                            world.getVertiWall().get(i).getHeight())
                    && (world.getAuber().getX() - addedX - world.getVertiWall().get(i).getWidth() ==
                        world.getVertiWall().get(i).getX())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRightBlockCollission(int addedX) {
        for (int i = 0; i < world.getHorizWall().size; i++) {
            if ((world.getAuber().getY() >= world.getHorizWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getHorizWall().get(i).getY() +
                            world.getHorizWall().get(i).getHeight())
                    && (world.getAuber().getX() + addedX + world.getAuber().getWidth() ==
                        world.getHorizWall().get(i).getX())) {
                return true;
            }
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            if ((world.getAuber().getY() >= world.getVertiWall().get(i).getY() - world.getAuber().getHeight()) &&
                    (world.getAuber().getY() <= world.getVertiWall().get(i).getY() +
                            world.getVertiWall().get(i).getHeight()) &&
                    (world.getAuber().getX() + addedX + world.getAuber().getWidth() ==
                            world.getVertiWall().get(i).getX())) {
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
                standingOnTelePad = true;
            }
        }
    }

    public void checkHealPadStop(){
        if (((world.getAuber().getX() >= world.getHealingPad().getX()) && ((world.getAuber().getX() <=
                world.getHealingPad().getX()+40))) && ((world.getAuber().getY() >= world.getHealingPad().getY())) &&
                ((world.getAuber().getY() <= world.getHealingPad().getX()+40))) {
            standingOnHealPad = true;
        }
    }

    public boolean getStandingOnTelePad() {
        return standingOnTelePad;
    }

    public boolean getStandingOnHealPad() {
        return standingOnHealPad;
    }
}
