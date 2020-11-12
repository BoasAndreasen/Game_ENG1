package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class World {
    private Auber auber;
    public Array<Block> blocks = new Array<Block>();
    public Array<Block> hostiles = new Array<Block>();
    public Array<System> systems = new Array<System>();
    public Array<HealthBar>systemhealthbars= new Array<HealthBar>();
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
        auberhealthbar=new HealthBar(0,0);

        //creating a test teleport pad
        testtelepad=new TeleportPad(0,0);

        //creating a healingPad
        healingPad= new HealPad(0,0);



        auber = new Auber(0, 0, "left");
        
        blocks.add(new Block(300, 300));
        blocks.add(new Block(400, 400));

        infiltrator = new Infiltrator(500, 300, "left");

    }

    //INFILTRATOR - Brian
    
    
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

    public Auber getAuber() {return auber;}

    public Array<System> getSystems(){return systems;}

    public Array<HealthBar> getSystemhealthbars(){return systemhealthbars;}
    public Array<Block> getBlocks(){return blocks;}

    public HealthBar getAuberhealthbar(){return auberhealthbar;}

    public Infiltrator getInfiltrator() {return infiltrator;}

    public TeleportPad getTesttelepad(){return testtelepad;}

    public HealPad getHealingPad(){return healingPad;}


}
