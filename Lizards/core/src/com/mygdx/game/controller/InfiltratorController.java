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
            if (world.getInfiltrators().get(i).isCurrent()) { //if the infiltrator we want is current
                currentAbility = world.getInfiltrators().get(i);



                if (currentAbility.getAbility().equals("bombs")) { //throws 3 bombs at auber or system
                    Abilitytask = new Timer.Task() {
                        @Override
                        public void run() {
                            if (world.getAuber().closeToInfiltrator(currentAbility.getX(),currentAbility.getY())) {
                                if (world.getBomb().randBomb() == 1)  //throw bomb at auber if he is in a radius
                                    { world.getAuber().decHealth(20);}

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
                    };

                    //throw 4 bombs at 4 sec intervals
                    timer.scheduleTask(Abilitytask, 5, 4, 3);
                }

                if (currentAbility.getAbility().equals("corrupt")) {
                    Abilitytask = new Timer.Task() {
                        @Override
                        public void run() {
                            for (int a = 0; a < world.getSystems().size; a++) {
                                if ((world.getAuber().closeToSystem(world.getSystems().get(a))) &&
                                        (currentAbility.closeToSystem(world.systems.get(a)))) {
                                    world.getSystems().get(a).setHealth(20);
                                }
                            }
                        }
                    };
                    // auber damage the system 3 times
                    timer.scheduleTask(Abilitytask, 4, 4, 3);
                }

                if (currentAbility.getAbility() == "shield") {
                    //TODO
                }
            }
        }
    }

    public void NormalDamage() {
        //TODO Doesnt work
        for (int i = 0; i < world.getInfiltrators().size; i++) {
            if (world.getInfiltrators().get(i).isCurrent()) {
                normal= world.getInfiltrators().get(i);
                for (int j = 0; j < world.getSystems().size; j++) {
                    if ((world.getAuber().closeToInfiltrator(normal.getX(), normal.getY())) &&
                            ((normal.closeToSystem(world.systems.get(j))))) {
                        currentSys=world.getSystems().get(j);
                        if (world.getBomb().randBomb() == 1){
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
                        //10 damage randomly every 2 seconds
                        timer.scheduleTask(normalTask, 1, 3, 10);
                    }
                    else{
                            if (normal.closeToSystem(world.getSystems().get(j))){
                        currentSys=world.getSystems().get(i);
                        normalTask= new Timer.Task() {
                            @Override
                            public void run() {
                                currentSys.setHealth(10);
                            }
                        };
                        timer.scheduleTask(normalTask,1,3,10);}
                    }
                }
            }
        }
    }
}
