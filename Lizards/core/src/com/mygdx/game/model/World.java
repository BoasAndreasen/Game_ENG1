package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class World {
    private Auber auber;
    private Array<Block> blocks = new Array<Block>();
    private Array<Block> hostiles = new Array<Block>();
    private Array<System> systems = new Array<System>();
    private HealthBar Sys1HB;
    
    
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

        auber = new Auber(0, 0, "left");
        blocks.add(new Block(0, 1));
        Sys1HB= new HealthBar(300,-170,64,64);
        
        //INFILTRATOR - Brian
        infiltrator = new Infiltrator(64, 64, 64, 64,100, "left"); 

    }

    public Auber getAuber() {return auber;}

    public Array<System> getSystems(){return systems;}

    public HealthBar getHB(){return Sys1HB;}
    
    //INFILTRATOR - Brian
    public Infiltrator getInfiltrator() {return infiltrator;}


}
