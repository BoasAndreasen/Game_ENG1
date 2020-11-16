package com.mygdx.game.controller;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.Infiltrator;
import com.mygdx.game.model.System;
import com.mygdx.game.model.World;

public class InfiltratorController {

    private World world;
    private Timer timer = new Timer();
    private Timer.Task Abilitytask;
    private Timer.Task normalTask;
    private Infiltrator currentAbility;
    private Infiltrator normal;
    private System currentSys;


    public InfiltratorController(World world) {
        this.world = world;
    }


    public void Abilities() {
        for (int i = 0; i < world.getInfiltrators().size; i++) {
            if (world.getInfiltrators().get(i).isCurrent() == true) { //if the infiltrator we want is current
                currentAbility = world.getInfiltrators().get(i);

                if (currentAbility.getAbility() == "bombs") { //throws 3 bombs at auber or system
                    Abilitytask = new Timer.Task() {
                        @Override
                        public void run() {
                            if (world.getBomb().randBomb() == 1) { //throw bomb at auber if he is in 400 radius
                                if (world.getAuber().closeToHostile(currentAbility)) {
                                    world.getAuber().decHealth(20);

                                }
                            }
                            if (world.getBomb().randBomb() == 0) { //throw bomb at system
                                for (int a = 0; a < world.getSystems().size; a++) {
                                    if ((currentAbility.getX() == world.getSystems().get(a).getX()) && (currentAbility.getY() == world.getSystems().get(a).getY())) {
                                        world.getSystems().get(a).setHealth(20);
                                    }
                                }
                            }
                        }
                    };

                    timer.scheduleTask(Abilitytask, 2, 2, 3); //throw 3 bombs at 2 sec intervals

                }

                if (currentAbility.getAbility() == "corrupt") {
                    Abilitytask = new Timer.Task() {
                        @Override
                        public void run() {

                            for (int a = 0; a < world.getSystems().size; a++) {
                                if ((world.getAuber().closeToSystem(world.getSystems().get(a))) && (currentAbility.getX() == world.getSystems().get(a).getX()) && (currentAbility.getY() == world.getSystems().get(a).getY())) {
                                    world.getSystems().get(a).setHealth(20);
                                }
                            }

                        }
                    };
                    timer.scheduleTask(Abilitytask, 2, 2, 3); // auber damage the system 3 times
                }

                if (currentAbility.getAbility() == "shield") {
                }


            }
        }
    }

    public void NormalDamage() {
        for (int i = 0; i < world.getInfiltrators().size; i++) {
            if (world.getInfiltrators().get(i).isCurrent() == true) {
                normal = world.getInfiltrators().get(i);
                for (int j = 0; j < world.getSystems().size; j++) {
                    if ((world.getAuber().closeToHostile(normal)==true) && (world.getSystems().get(j).getX()==normal.getX()) && (world.getSystems().get(j).getY()==normal.getY())){
                        currentSys=world.getSystems().get(j);
                        if (world.getBomb().randBomb()==1){
                            normalTask= new Timer.Task() {
                                @Override
                                public void run() {
                                    world.getAuber().decHealth(10);
                                }
                            };
                        }
                        else{
                            normalTask= new Timer.Task() {
                                @Override
                                public void run() {
                                    currentSys.setHealth(10);
                                }
                            };
                        }
                        timer.scheduleTask(normalTask, 0, 0, 10); //10 damage randomly every 2 seconds
                    }
                    if((world.getAuber().closeToHostile(normal)==false)&& (world.getSystems().get(j).getX()==normal.getX()) && (world.getSystems().get(j).getY()==normal.getY())){
                        currentSys=world.getSystems().get(i);
                        normalTask= new Timer.Task() {
                            @Override
                            public void run() {
                                currentSys.setHealth(10);
                            }
                        };
                        timer.scheduleTask(normalTask,0,0,10);
                    }

                }
            }


        }

    } //doesnt work yet. not sure why

}
