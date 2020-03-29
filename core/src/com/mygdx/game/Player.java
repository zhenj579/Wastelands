package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Player extends BaseActor{
	
	private Animation<TextureRegion> up, down, left, right;
	private Animation<TextureRegion> sup, sdown, sleft, sright;
	private Animation<TextureRegion> cur = down;
	final float frameDuration = 0.25f;
	
	public Player(float x, float y, Stage s) {
		super(x, y, s);
		
		initAnim();
		
		setBoundaryPolygon(8);
		
        setAcceleration(45 * 4);
        setMaxSpeed(50);
        setDeceleration(45 * 4);
   
	}
	
	public void act(float dt) {
		
		super.act(dt);
		
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
        	cur = left;
            accelerateAtAngle(180);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	cur = right;
            accelerateAtAngle(0);
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
        	cur = up;
            accelerateAtAngle(90);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
        	cur = down;
            accelerateAtAngle(270);
        }
		
        if(getSpeed() == 0) {
        	if(cur == right) {
        		cur = sright;
        	}
        	if(cur == left) {
        		cur = sleft;
        	}
        	if(cur == up) {
        		cur = sup;
        	}
        	if(cur == down) {
        		cur = sdown;
        	}
        }
        
        setAnimation(cur);
        
//        System.out.println("Player X: " + getX() + " Player Y: " + getY());
        
        setAnimationPaused( !isMoving() );
        
		applyPhysics(dt);
		
		alignCamera();
		
		boundToWorld();
	}
	
	public void initAnim() {
		Texture east = new Texture("right.png");
		Texture west = new Texture("right.png");
		Texture south = new Texture("downv2.png");
		Texture north = new Texture("upv2.png");
		
		
		TextureRegion[][] temp = TextureRegion.split(east, 16, 16);
		
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		for (int c = 0; c < 4; c++)
		textureArray.add( temp[0][c] );
		
		right = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		textureArray.clear();
		textureArray.add(temp[0][0]);
		sright = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		
		textureArray.clear();
		temp = null;
		
		temp = TextureRegion.split(west, 16, 16);
		
		for(int i = 0; i < temp[0].length; i++) 
			temp[0][i].flip(true, false);

		for (int c = 0; c < 4; c++)
			textureArray.add( temp[0][c] );
		
		left = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		textureArray.clear();
		textureArray.add(temp[0][0]);
		sleft = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		//
		textureArray.clear();
		temp = null;
		//
		temp = TextureRegion.split(south, 16, 16);
		
		for (int c = 0; c < 4; c++)
		textureArray.add( temp[0][c] );
		
		down = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		textureArray.clear();
		textureArray.add(temp[0][3]);
		sdown = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		///
		textureArray.clear();
		temp = null;
		//
		temp = TextureRegion.split(north, 16, 16);
		for (int c = 0; c < 4; c++)
		textureArray.add( temp[0][c] );
		
		up = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		textureArray.clear();
		textureArray.add(temp[0][3]);
		sup = new Animation<TextureRegion>(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		//
		cur = down;
		setAnimation(cur);
	}
	
}
