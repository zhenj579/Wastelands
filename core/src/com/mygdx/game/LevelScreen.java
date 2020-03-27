package com.mygdx.game;

public class LevelScreen extends BaseScreen
{
    Map map;
	private Player wastelander;
	
    public void initialize() 
    {   
    	map = new Map(mainStage);
    	wastelander = new Player(100, 100, mainStage);
    }

    public void update(float dt)
    {
    	mainStage.act(dt);
    }
    
}