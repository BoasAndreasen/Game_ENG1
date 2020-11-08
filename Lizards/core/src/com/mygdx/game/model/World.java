package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class World {
    private Array<Block> blocks = new Array<Block>();
    private Array<Block> hostiles = new Array<Block>();
    private Auber auber;
    private System sys1;
    private System sys2;
    private System sys3;
    private System sys4;
    private System sys5;
    private System sys6;
    private System sys7;
    private System sys8;
    private System sys9;
    private System sys10;
    private System sys11;
    private System sys12;
    private System sys13;
    private System sys14;
    private System sys15;

    private HealthBar HB;


    public World() {
        createWorld();
    }

    private void createWorld() {
        auber = new Auber(0, 0, 64, 64,1, "left");
        blocks.add(new Block(0, 1, 64, 64));

        //systems created below all with x=0,y=0 for now
        sys1= new System(300,300,64,64,100,false);
        sys2= new System(500,400,64,64,100,false);
        sys3= new System(0,0,64,64,100,false);
        sys4= new System(0,0,64,64,100,false);
        sys5= new System(0,0,64,64,100,false);
        sys6= new System(0,0,64,64,100,false);
        sys7= new System(0,0,64,64,100,false);
        sys8= new System(0,0,64,64,100,false);
        sys9= new System(0,0,64,64,100,false);
        sys10= new System(0,0,64,64,100,false);
        sys11= new System(0,0,64,64,100,false);
        sys12= new System(0,0,64,64,100,false);
        sys13= new System(0,0,64,64,100,false);
        sys14= new System(0,0,64,64,100,false);
        sys15= new System(0,0,64,64,100,false);

        HB= new HealthBar(400,-170,64,64);

    }

    public Auber getAuber() {return auber;}

    public System getSys1(){return sys1;}
    public System getSys2(){return sys2;}
    public System getSys3(){return sys3;}
    public System getSys4(){return sys4;}
    public System getSys5(){return sys5;}
    public System getSys6(){return sys6;}
    public System getSys7(){return sys7;}
    public System getSys8(){return sys8;}
    public System getSys9(){return sys9;}
    public System getSys10(){return sys10;}
    public System getSys11(){return sys11;}
    public System getSys12(){return sys12;}
    public System getSys13(){return sys13;}
    public System getSys14(){return sys14;}
    public System getSys15(){return sys15;}

    public HealthBar getHB(){return HB;}


}
