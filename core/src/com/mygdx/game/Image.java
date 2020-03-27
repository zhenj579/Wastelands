package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Image extends BaseActor{

	public Image(String filepath, float x, float y, Stage s) {
		super(x, y, s);
		
        setAcceleration(40*4);
        setMaxSpeed(50);
        setDeceleration(40*4);
        
		String[] files = {filepath};
		loadAnimationFromFiles(files, 0.1f, true);
	}
	
	public void act(float dt) {
		applyPhysics(dt);
	}
	
	
}
