package com.mygdx.game;

public class Wastelanders extends BaseGame {
	
    public void create() 
    {        
        super.create();

        setActiveScreen( new LevelScreen() );
    }
	
}
