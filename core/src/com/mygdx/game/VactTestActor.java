package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class VactTestActor extends BaseActor {
	
	private Animation<TextureRegion> anim;
	private boolean vacAble = true;
	
	public VactTestActor(float x, float y, Stage s) {
		super(x, y, s);
		this.setMaxSpeed(40);
		this.setAcceleration(380);
		this.setDeceleration(380);
		initAnim();
	}
	
	public void act(float dt) {
		super.act(dt);
		setAnimation(anim);
		if(vacAble && LevelScreen.wastelander.isVacUpgrd() && this.isWithinDistance(LevelScreen.wastelander.getVacDistance(), LevelScreen.wastelander)) {
			Vector2 playerToActorVector = new Vector2(LevelScreen.wastelander.getX() - this.getX(), LevelScreen.wastelander.getY() - this.getY());
			this.accelerateAtAngle(playerToActorVector.angle());
			this.applyPhysics(dt);
		}
	}
	
	public void initAnim() {
		Texture sprite = new Texture("merchant_closeup.png");
		TextureRegion[][] tr = TextureRegion.split(sprite, 16, 16);
		
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		
		for (int c = 0; c < 1; c++)
			textureArray.add( tr[0][c]);
		
		anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		
		setAnimation(anim);
	}
	
}