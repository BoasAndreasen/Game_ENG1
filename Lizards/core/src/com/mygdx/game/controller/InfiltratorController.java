package com.mygdx.game.controller;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.Infiltrator;
import com.mygdx.game.model.System;
import com.mygdx.game.model.World;
import org.lwjgl.Sys;

public class InfiltratorController {

    private final World world;

    public InfiltratorController(World world) {
        this.world = world;
    }

    public void Abilities() {
        for (int i = 0; i < world.getInfiltrators().size; i++) {
            if (world.getInfiltrators().get(i).isCurrent() && (!(world.getInfiltrators().get(i).isArrested()))) {
                //if the infiltrator we want is current
                Infiltrator currentAbility = world.getInfiltrators().get(i);

                if (currentAbility.getAbility().equals("bombs")) { //throws 3 bombs at auber or system
                    if (world.getAuber().closeToCharacterNPC(currentAbility)) {
                        if (world.getBomb().randBomb() == 1) {  //throw bomb at auber if he is in a radius
                             world.getAuber().decHealth(30);
                        }
                        else{
                            for (int a = 0; a < world.getSystems().size; a++) {
                                if (currentAbility.closeToSystem(world.getSystems().get(a))) {
                                    world.getSystems().get(a).decHealth(30);
                                }
                            }
                        }
                    }
                    else{
                        for (int a = 0; a < world.getSystems().size; a++) {
                            if (currentAbility.closeToSystem(world.getSystems().get(a))) {
                                world.getSystems().get(a).decHealth(20);
                            }
                        }
                    }
                }

                else if (currentAbility.getAbility().equals("corrupt")) {
                    for (int a = 0; a < world.getSystems().size; a++) {
                        if ((world.getAuber().closeToSystem(world.getSystems().get(a))) &&
                                (currentAbility.closeToSystem(world.systems.get(a)))) {
                            world.getSystems().get(a).decHealth(20);
                        }
                    }
                }


            };
        }
    };

    public void NormalDamage() {
        for (int i = 0; i < world.getInfiltrators().size; i++) {
            if (world.getInfiltrators().get(i).isCurrent() && (!(world.getInfiltrators().get(i).isArrested()))) {
                Infiltrator normal = world.getInfiltrators().get(i);
                for (int j = 0; j < world.getSystems().size; j++) {
                    System currentSys;
                    if ((world.getAuber().closeToCharacterNPC(normal)) &&
                            ((normal.closeToSystem(world.systems.get(j))))) {
                        currentSys = world.getSystems().get(j);
                        if (world.getBomb().randBomb() == 1){
                            world.getAuber().decHealth(20);
                        }
                        else{
                            currentSys.decHealth(20);
                        }
                    }
                    else{
                        if (normal.closeToSystem(world.systems.get(j))){
                            currentSys = world.systems.get(j);
                            currentSys.decHealth(20);
                        }
                    }
                }
            }
        }
    }

    //takes current X and current Y
    public int findNearestSystem(float cx, float cy) {
        int nearSystem = 0; //nearest system
        double nearDistance = 0; //nearest distance
        double tempDistance;

        for (int i = 0; i < world.getSystems().size; i++) {
            if (!(world.getSystems().get(i).isDestroyed())) {
                tempDistance = findDistance(cx, world.getSystems().get(i).getX(),
                        cy, world.getSystems().get(i).getY());

                if (i != 0) {
                    if(tempDistance < nearDistance) {
                        nearDistance = tempDistance;
                        nearSystem = i;
                    }
                }
                else {
                    nearDistance = tempDistance;
                }
            }
        }
        return nearSystem;
    }

    public double findDistance(float aX, float aY, float bX, float bY) {
        float dx = Math.abs(aX - aY);
        float dy = Math.abs(bX - bY);
        double dt = Math.sqrt((dx*dx)+(dy*dy));

        return dt;
    }

    public void updateInfiltratorLocation() {
        for (int y = 0; y < world.infiltrators.size; y++){
            int k = findNearestSystem(world.getInfiltrators().get(y).getX(), world.getInfiltrators().get(y).getY());

            if (world.getInfiltrators().get(y).getX() > world.getSystems().get(k).getX() &&
                    (!(world.getInfiltrators().get(y).isArrested()))) {
                world.getInfiltrators().get(y).addX(-3);
            }

            if (world.getInfiltrators().get(y).getX() < world.getSystems().get(k).getX() &&
                    (!(world.getInfiltrators().get(y).isArrested()))) {
                world.getInfiltrators().get(y).addX(3);
            }

            if (world.getInfiltrators().get(y).getY() > world.getSystems().get(k).getY() &&
                    (!(world.getInfiltrators().get(y).isArrested()))) {
                world.getInfiltrators().get(y).addY(-3);
            }

            if (world.getInfiltrators().get(y).getY() < world.getSystems().get(k).getY() &&
                    (!(world.getInfiltrators().get(y).isArrested()))) {
                world.getInfiltrators().get(y).addY(3);
            }
        }
    }
}
