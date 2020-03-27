package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class LevelScreen extends BaseScreen
{
    
	private Player wastelander;
	Image de;
	
    public void initialize() 
    {   
    	//BaseActor.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	wastelander = new Player(0, 0, mainStage);
    	de = new Image("demoneye.png", 0, 0, mainStage);
    }

    public void update(float dt)
    {
    	mainStage.act(dt);
        if (Gdx.input.isKeyPressed(Keys.A)) {
            de.accelerateAtAngle(180);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            de.accelerateAtAngle(0);
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            de.accelerateAtAngle(90);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            de.accelerateAtAngle(270);
        }
    }
    
}