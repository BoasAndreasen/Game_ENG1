package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class World {
    private Auber auber;
    private Array<Block> blocks = new Array<Block>();
    private Array<Block> hostiles = new Array<Block>();
    private Array<System> systems = new Array<System>();
    private Array<HealthBar>systemhealthbars= new Array<HealthBar>();
    private HealthBar auberhealthbar;
    private TeleportPad testtelepad;
    private HealPad healingPad;
    
    
    //INFILTRATOR - Brian
    private Infiltrator infiltrator;


    public World() {
        createWorld();
    }

    private void createWorld() {
        //systems created below all with x=0,y=0 for now
        systems.add(new System(300,300,100,false));
        systems.add(new System(300,400,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));
        systems.add(new System(0,0,100,false));

        //system health bars created below all with x=0,y=0 for now
        //health bar [0] corresponds to system [0]

        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));
        systemhealthbars.add(new HealthBar(0,0));



        // Aubers health bar - not rendered as may want a different texture
        auberhealthbar=new HealthBar(0,0);

        //creating a test teleport pad
        testtelepad=new TeleportPad(0,0);

        //creating a healingPad
        healingPad= new HealPad(0,0);


        auber = new Auber(0, 0, "left");
        blocks.add(new Block(0, 1));
        infiltrator = new Infiltrator(10, 10, "left");

    }

    public Auber getAuber() {return auber;}

    public Array<System> getSystems(){return systems;}

    public Array<HealthBar> getSystemhealthbars(){return systemhealthbars;}

    public HealthBar getAuberhealthbar(){return auberhealthbar;}

    public Infiltrator getInfiltrator() {return infiltrator;}

    public TeleportPad getTesttelepad(){return testtelepad;}

    public HealPad getHealingPad(){return healingPad;}


}
