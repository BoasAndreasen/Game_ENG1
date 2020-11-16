package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class Node extends Rectangle {
	private String nodeName;
    private final float x;
    private final float y;
    private boolean isDoor;
    private String direction;
    
    public Node(String nodeName, int x, int y, boolean isDoor, String direction){
    	this.nodeName = nodeName;
        this.x = x;
        this.y = y;
        this.isDoor = isDoor;
        this.direction = direction;
        
    }
    
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
    
    public String nodeName() 
    {
    	return nodeName;
    }
    
    public boolean isDoor() 
    {
    	return isDoor;
    }
    
    public String direction() 
    {
    	return direction;  	
    }
}
