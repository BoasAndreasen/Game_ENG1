package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import sun.tools.jar.Main;

import java.util.*;

public class World {
    private Auber auber;
    public Array<HorizWall> horizWall = new Array<HorizWall>();
    public Array<VertiWall> vertiWall = new Array<VertiWall>();
    public Array<Infiltrator> hostiles = new Array<Infiltrator>();
    public Array<System> systems = new Array<System>();
    public Array<TeleportPad> telePads = new Array<TeleportPad>();
    private HealPad healingPad;
    private Bomb bomb;
    private Infiltrator currentHostile;
    private Timer timer;
    private Timer.Task task;

    public World() {
        createWorld();
    }

    private void createWorld() {
        //systems and their corresponding health bars
        List<Integer> systemX = Arrays.asList(600, 400, 1100, 700, 1500, 1500, 2300, 2300,
                300, 600, 700, 1300, 1200, 2300, 2300);
        List<Integer> systemY = Arrays.asList(800, 600, 900, 600, 900, 600, 1000, 800,
                200, 500, 500, 500, 200, 500, 0);

        //add hostiles
        hostiles.add(new Infiltrator(100,300,"left","bombs",false));
        hostiles.add(new Infiltrator(0,0,"left","bombs",false));
        hostiles.add(new Infiltrator(0,0,"left","invisible",false));
        hostiles.add(new Infiltrator(0,0,"left","invisible",false));
        hostiles.add(new Infiltrator(0,0,"left","none",false));
        hostiles.add(new Infiltrator(0,0,"left","none",false));
        hostiles.add(new Infiltrator(0,0,"left","shield",false));
        hostiles.add(new Infiltrator(0,0,"left","shield",false));


        //bomb
        bomb = new Bomb();


        timer= new Timer();

        for (int i=0; i < systemX.size(); i++) {
            systems.add(new System(systemX.get(i), systemY.get(i), 100, false));
        }





        //creating teleport pads
        List<Integer> telepadX = Arrays.asList(200, 1100, 1200, 2300, 600, 1100, 1500, 2300);
        List<Integer> telepadY = Arrays.asList(800, 600, 900, 600, 200, 500, 500, 200);
        for (int i=0; i < telepadX.size(); i++) {
            telePads.add(new TeleportPad(telepadX.get(i), telepadY.get(i))); }

        //creating a healing pad
        healingPad = new HealPad(900,500);

        auber = new Auber(0, 0, "left",100);



        //screen 1 walls
        List<Integer> screen1HorizWallsX = Arrays.asList(200, 300, 400, 500, 600, 700, 800,
                900, 1000, 1100, 200, 300, 400, 500, 600, 200, 500, 600, 700, 1000, 1100);
        List<Integer> screen1HorizWallsY = Arrays.asList(600, 600, 600, 600, 600, 600, 600,
                600, 600, 600, 800, 800, 800, 800, 800, 1000, 1000, 1000, 1000, 1000, 1000);
        List<Integer> screen1VertiWallsX = Arrays.asList(200, 200, 700, 700, 700, 700, 1190,
                1190, 1190, 1190);
        List<Integer> screen1VertiWallsY = Arrays.asList(800, 900, 600, 700, 800, 900, 600,
                700, 800, 900);
        for (int i=0; i < screen1HorizWallsX.size(); i++) {
            horizWall.add(new HorizWall(screen1HorizWallsX.get(i), screen1HorizWallsY.get(i))); }
        for (int i=0; i < screen1VertiWallsX.size(); i++) {
            vertiWall.add(new VertiWall(screen1VertiWallsX.get(i), screen1VertiWallsY.get(i))); }

        //screen 2 walls
        List<Integer> screen2HorizWallsX = Arrays.asList(1200, 1500, 1800, 1900, 2000, 2300, 1800,
                2100, 2200, 2300, 1200, 1300, 1400, 1500, 1800, 1900, 2000, 2100, 2200, 2300);
        List<Integer> screen2HorizWallsY = Arrays.asList(1000, 1000, 1000, 1000, 1000, 1000, 800,
                800, 800, 800, 600, 600, 600, 600, 600, 600, 600, 600, 600, 600);
        List<Integer> screen2VertiWallsX = Arrays.asList(1200, 1200, 1200, 1200, 1600, 1600, 1600,
                1600, 1800, 1800, 1800, 1800);
        List<Integer> screen2VertiWallsY = Arrays.asList(600, 700, 800, 900, 600, 700, 800, 900,
                600, 700, 1000, 1100);
        for (int i=0; i < screen2HorizWallsX.size(); i++) {
            horizWall.add(new HorizWall(screen2HorizWallsX.get(i), screen2HorizWallsY.get(i))); }
        for (int i=0; i < screen2VertiWallsX.size(); i++) {
            vertiWall.add(new VertiWall(screen2VertiWallsX.get(i), screen2VertiWallsY.get(i))); }

        //screen 3 walls
        List<Integer> screen3HorizWallsX = Arrays.asList(200, 300, 400, 500, 600, 1100, 200,
                300, 400, 500, 600, 700, 800, 900, 1000, 1100);
        List<Integer> screen3HorizWallsY = Arrays.asList(200, 200, 200, 200, 200, 200, 590,
                590, 590, 590, 590, 590, 590, 590, 590, 590);
        List<Integer> screen3VertiWallsX = Arrays.asList(200, 200, 700, 700, 700, 700, 900,
                900, 900, 900, 1190, 1190, 1190, 1190);
        List<Integer> screen3VertiWallsY = Arrays.asList(200, 300, 200, 300, 400, 500, 200,
                300, 400, 500, 200, 300, 400, 500);
        for (int i=0; i < screen3HorizWallsX.size(); i++) {
            horizWall.add(new HorizWall(screen3HorizWallsX.get(i), screen3HorizWallsY.get(i))); }
        for (int i=0; i < screen3VertiWallsX.size(); i++) {
            vertiWall.add(new VertiWall(screen3VertiWallsX.get(i), screen3VertiWallsY.get(i))); }

        //screen 4 walls
        List<Integer> screen4HorizWallsX = Arrays.asList(1200, 1300, 1400, 1500, 1800, 1900,
                2000, 2100, 2200, 2300, 1200, 1300, 1400, 1500, 1800, 2100, 2200, 2300);
        List<Integer> screen4HorizWallsY = Arrays.asList(590, 590, 590, 590, 590, 590, 590,
                590, 590, 590, 200, 200, 200, 200, 200, 200, 200, 200);
        List<Integer> screen4VertiWallsX = Arrays.asList(1200, 1200, 1200, 1200, 1600, 1600,
                1800, 1800, 1800, 1800);
        List<Integer> screen4VertiWallsY = Arrays.asList(200, 300, 400, 500, 200, 500, 200,
                300, 400, 500);
        for (int i=0; i < screen4HorizWallsX.size(); i++) {
            horizWall.add(new HorizWall(screen4HorizWallsX.get(i), screen4HorizWallsY.get(i))); }
        for (int i=0; i < screen4VertiWallsX.size(); i++) {
            vertiWall.add(new VertiWall(screen4VertiWallsX.get(i), screen4VertiWallsY.get(i))); }


        for (int a=0;a<hostiles.size;a++){
            if (hostiles.get(a).getAbility()=="bombs"){ //throws 3 bombs at auber or system
                currentHostile=hostiles.get(a);
                task=new Timer.Task() {
                    @Override
                    public void run() {
                        if (bomb.randBomb()==1) { //throw bomb at auber if he is in 400 radius
                            if (((auber.getX()>currentHostile.getX())&& (auber.getX()<currentHostile.getX()+400) && ((auber.getY()>currentHostile.getY())&& (auber.getY()<currentHostile.getY()+400)))||
                                    ((auber.getX()<currentHostile.getX())&& (auber.getX()>currentHostile.getX()-400)&& (auber.getY()>currentHostile.getY())&& (auber.getY()<currentHostile.getY()+400)) ||
                                    ((auber.getX()>currentHostile.getX())&& (auber.getX()<currentHostile.getX()+400) && ((auber.getY()<currentHostile.getY())&& (auber.getY()>currentHostile.getY()-400)))||
                                    ((auber.getX()<currentHostile.getX())&& (auber.getX()>currentHostile.getX()-400)&& (auber.getY()<currentHostile.getY())&& (auber.getY()>currentHostile.getY()-400))){
                                auber.setHealth(20);
                            }
                        }
                        if (bomb.randBomb()==0) { //throw bomb at system
                            for (int a=0 ; a< systems.size;a++){
                                if ((currentHostile.getX()==systems.get(a).getX())&& (currentHostile.getY()==systems.get(a).getY())){
                                    systems.get(a).setHealth(20);
                                }
                            }
                        }
                    }
                };

                timer.scheduleTask(task,2,2,3); //throw 3 bombs at 2 sec intervals

            }}

    }



    public void updateInfiltratorLocationX() {
        for (int y=0;y<this.hostiles.size;y++){
            if (this.getInfiltrators().get(y).getX() > this.getSystems().get(0).getX())
            {

                this.getInfiltrators().get(y).addX(-5);
            }

            if (this.getInfiltrators().get(y).getX() < this.getSystems().get(0).getX())
            {
                this.getInfiltrators().get(y).addX(5);
            }


        }

    }

    public void updateInfiltratorLocationY() {
        for (int y=0;y<this.hostiles.size;y++){
        if (this.getInfiltrators().get(y).getY() > this.getSystems().get(0).getY())
        {
            this.getInfiltrators().get(y).addY(-5);
        }

        if (this.getInfiltrators().get(y).getY() < this.getSystems().get(0).getY())
        {
            this.getInfiltrators().get(y).addY(5);
        }}
    }



    public Auber getAuber() {
        return auber;
    }

    public Array<System> getSystems(){
        return systems;
    }


    public Array<HorizWall> getHorizWall(){return horizWall;}

    public Array<VertiWall> getVertiWall(){return vertiWall;}

    public Array<TeleportPad> getTelePads(){return telePads;}

    public Array<Infiltrator> getInfiltrators() {return hostiles;}

    public HealPad getHealingPad(){return healingPad;}

    public Bomb getBomb(){return bomb;}







}
