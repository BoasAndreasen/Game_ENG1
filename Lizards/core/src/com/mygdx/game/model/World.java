package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import java.util.*;

public class World {
    private Auber auber;
    public Array<HorizWall> horizWall = new Array<HorizWall>();
    public Array<VertiWall> vertiWall = new Array<VertiWall>();
    //public Array<Infiltrator> hostiles = new Array<Infiltrator>();
    public Array<System> systems = new Array<System>();
    public Array<TeleportPad> telePads = new Array<TeleportPad>();
    private HealPad healingPad;
    private Infiltrator infiltrator;
    private Bomb bomb;
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

        infiltrator = new Infiltrator(100, 300, "left", "bombs",false);
        //bomb
        bomb = new Bomb(infiltrator.getX(), infiltrator.getY());
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



        if (infiltrator.getAbility()=="bombs"){ //throws 3 bombs at auber or system
            task=new Timer.Task() {
                @Override
                public void run() {
                    if (bomb.randBomb()==1) { //throw bomb at auber if he is in 400 radius
                        if (((auber.getX()>infiltrator.getX())&& (auber.getX()<infiltrator.getX()+400) && ((auber.getY()>infiltrator.getY())&& (auber.getY()<infiltrator.getY()+400)))||
                                ((auber.getX()<infiltrator.getX())&& (auber.getX()>infiltrator.getX()-400)&& (auber.getY()>infiltrator.getY())&& (auber.getY()<infiltrator.getY()+400)) ||
                                ((auber.getX()>infiltrator.getX())&& (auber.getX()<infiltrator.getX()+400) && ((auber.getY()<infiltrator.getY())&& (auber.getY()>infiltrator.getY()-400)))||
                                ((auber.getX()<infiltrator.getX())&& (auber.getX()>infiltrator.getX()-400)&& (auber.getY()<infiltrator.getY())&& (auber.getY()>infiltrator.getY()-400))){
                            auber.setHealth(20);
                        }
                    }
                    if (bomb.randBomb()==0) { //throw bomb at system
                        for (int a=0 ; a< systems.size;a++){
                            if ((infiltrator.getX()==systems.get(a).getX())&& (infiltrator.getY()==systems.get(a).getY())){
                                systems.get(a).setHealth(20);
                            }
                        }
                    }
                }
            };

            timer.scheduleTask(task,2,2,3); //throw 3 bombs at 2 sec intervals

        }

    }



    public void updateInfiltratorLocationX() {
        if (this.getInfiltrator().getX() > this.getSystems().get(0).getX())
        {
        	
            this.getInfiltrator().addX(-5);
        }

        if (this.getInfiltrator().getX() < this.getSystems().get(0).getX())
        {
            this.getInfiltrator().addX(5);
        }
    }

    public void updateInfiltratorLocationY() {
        if (this.getInfiltrator().getY() > this.getSystems().get(0).getY())
        {
            this.getInfiltrator().addY(-5);
        }

        if (this.getInfiltrator().getY() < this.getSystems().get(0).getY())
        {
            this.getInfiltrator().addY(5);
        }
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

    public Infiltrator getInfiltrator() {return infiltrator;}

    public HealPad getHealingPad(){return healingPad;}

    public Bomb getBomb(){return bomb;}






}
