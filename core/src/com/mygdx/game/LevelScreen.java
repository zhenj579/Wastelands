package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class LevelScreen extends BaseScreen
{
    Map map;
	private Player wastelander;
	
    public void initialize() 
    {   
    	map = new Map(mainStage);
    	wastelander = new Player(600, 400, mainStage);
    }

    public void update(float dt)
    {
    	Vector2 wastelanderCoordinates = new Vector2((int)wastelander.getX(), (int)wastelander.getY());
    	WastelandTrash trash = map.getTrashAt(wastelanderCoordinates);
    	if(trash != null)
    	{
    		System.out.println(trash.getName());
    	}
    	mainStage.act(dt);
    }
    
}