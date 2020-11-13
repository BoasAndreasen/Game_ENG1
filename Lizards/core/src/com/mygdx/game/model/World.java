package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;
import java.util.*;
import com.mygdx.game.controller.AuberController;

public class World {
    private Auber auber;
    public Array<HorizWall> horizWall = new Array<HorizWall>();
    public Array<VertiWall> vertiWall = new Array<VertiWall>();
    //public Array<Infiltrator> hostiles = new Array<Infiltrator>();
    public Array<System> systems = new Array<System>();
    public Array<HealthBar>systemhealthbars= new Array<HealthBar>();
    public Array<TeleportPad> telePads = new Array<TeleportPad>();
    private HealthBar auberhealthbar;
    private HealPad healingPad;
    private Infiltrator infiltrator;

    public World() {
        createWorld();
    }

    private void createWorld() {
        //systems created below all with x=0,y=0 for now
        systems.add(new System(300,300,100,false)); //0
        systems.add(new System(300,400,100,false)); //1
        systems.add(new System(400,500,100,false)); //2
        systems.add(new System(0,0,100,false)); //3
        systems.add(new System(0,0,100,false)); //4
        systems.add(new System(0,0,100,false)); //5
        systems.add(new System(0,0,100,false)); //6
        systems.add(new System(0,0,100,false)); //7
        systems.add(new System(0,0,100,false)); //8
        systems.add(new System(0,0,100,false)); //9
        systems.add(new System(0,0,100,false)); //10
        systems.add(new System(0,0,100,false)); //11
        systems.add(new System(0,0,100,false)); //12
        systems.add(new System(0,0,100,false)); //13
        systems.add(new System(0,0,100,false)); //14

        //system health bars created below all with x=0,y=0 for now
        //health bar [0] corresponds to system [0]
        systemhealthbars.add(new HealthBar(0,0)); //0
        systemhealthbars.add(new HealthBar(0,0)); //1
        systemhealthbars.add(new HealthBar(0,0)); //2
        systemhealthbars.add(new HealthBar(0,0)); //3
        systemhealthbars.add(new HealthBar(0,0)); //4
        systemhealthbars.add(new HealthBar(0,0)); //5
        systemhealthbars.add(new HealthBar(0,0)); //6
        systemhealthbars.add(new HealthBar(0,0)); //7
        systemhealthbars.add(new HealthBar(0,0)); //8
        systemhealthbars.add(new HealthBar(0,0)); //9
        systemhealthbars.add(new HealthBar(0,0)); //10
        systemhealthbars.add(new HealthBar(0,0)); //11
        systemhealthbars.add(new HealthBar(0,0)); //12
        systemhealthbars.add(new HealthBar(0,0)); //13
        systemhealthbars.add(new HealthBar(0,0)); //14

        // Aubers health bar - not rendered as may want a different texture
        auberhealthbar = new HealthBar(0,0);

        //creating a test teleport pad
        telePads.add(new TeleportPad(1000,300));
        telePads.add(new TeleportPad(1000,0));

        //creating a healingPad
        healingPad = new HealPad(0,0);

        auber = new Auber(0, 0, "left");

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

        infiltrator = new Infiltrator(100, 300, "left");
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

    public Array<HealthBar> getSystemhealthbars(){
        return systemhealthbars;
    }

    public Array<HorizWall> getHorizWall(){return horizWall;}

    public Array<VertiWall> getVertiWall(){return vertiWall;}

    public Array<TeleportPad> getTelePads(){return telePads;}

    public HealthBar getAuberhealthbar(){return auberhealthbar;}

    public Infiltrator getInfiltrator() {return infiltrator;}

    public HealPad getHealingPad(){return healingPad;}


}
