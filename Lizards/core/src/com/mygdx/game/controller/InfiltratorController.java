package com.mygdx.game.controller;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.Infiltrator;
import com.mygdx.game.model.System;
import com.mygdx.game.model.World;
import org.lwjgl.Sys;

public class InfiltratorController {

    private World world;
    private Infiltrator currentAbility;
    private Infiltrator normal;
    private System currentSys;

    public InfiltratorController(World world) {
        this.world = world;
    }

    public void Abilities() {
        for (int i = 0; i < world.getInfiltrators().size; i++) {
            if (world.getInfiltrators().get(i).isCurrent() && (!(world.getInfiltrators().get(i).isArrested()))) { //if the infiltrator we want is current
                currentAbility = world.getInfiltrators().get(i);

                if (currentAbility.getAbility().equals("bombs")) { //throws 3 bombs at auber or system
                    if (world.getAuber().closeToInfiltrator(currentAbility.getX(),currentAbility.getY())) {
                        if (world.getBomb().randBomb() == 1) {  //throw bomb at auber if he is in a radius
                             world.getAuber().decHealth(20);
                        }
                        else{
                            for (int a = 0; a < world.getSystems().size; a++) {
                                if (currentAbility.closeToSystem(world.getSystems().get(a))) {
                                    world.getSystems().get(a).setHealth(20);
                                }
                            }
                        }
                    }
                    else{
                        for (int a = 0; a < world.getSystems().size; a++) {
                            if (currentAbility.closeToSystem(world.getSystems().get(a))) {
                                world.getSystems().get(a).setHealth(20);
                            }
                        }
                    }
                }

                else if (currentAbility.getAbility().equals("corrupt")) {
                    for (int a = 0; a < world.getSystems().size; a++) {
                        if ((world.getAuber().closeToSystem(world.getSystems().get(a))) &&
                                (currentAbility.closeToSystem(world.systems.get(a)))) {
                            world.getSystems().get(a).setHealth(20);
                        }
                    }
                }

                else if (currentAbility.getAbility().equals("shield")) {
                    //TODO
                }
            };
        }
    };

    public void NormalDamage() {
        for (int i = 0; i < world.getInfiltrators().size; i++) {
            if (world.getInfiltrators().get(i).isCurrent() && (!(world.getInfiltrators().get(i).isArrested()))) {
                normal= world.getInfiltrators().get(i);
                for (int j = 0; j < world.getSystems().size; j++) {
                    if ((world.getAuber().closeToInfiltrator(normal.getX(), normal.getY())) &&
                            ((normal.closeToSystem(world.systems.get(j))))) {
                        currentSys=world.getSystems().get(j);
                        if (world.getBomb().randBomb() == 1){
                            world.getAuber().decHealth(10);
                        }
                        else{
                            currentSys.setHealth(10);
                        }
                    }
                    else{
                        if (normal.closeToSystem(world.systems.get(j))){
                            currentSys=world.systems.get(j);
                            currentSys.setHealth(10);
                        }
                    }
                }
            }
        }
    }
}
