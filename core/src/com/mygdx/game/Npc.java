package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class Npc extends BaseActor {
	
	private Animation<TextureRegion> anim;
	private static final int animFrames = 2;
	private static final float animTime = 0.6f;
	private Shop shop;
	
	public Npc(float x, float y, Stage s, Table uiTable) {
		super(x, y, s);
		initAnim();
		setAnimation(anim);
		shop = new Shop(uiTable);
	}
	
	public void act(float dt) {
		super.act(dt);
		setAnimation(anim);
	}
	
	public void initAnim() {
		Texture sprite = new Texture("merchant.png");
		TextureRegion[][] tr = TextureRegion.split(sprite, 16, 16);
		
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		
		for (int c = 0; c < 2; c++)
			textureArray.add( tr[0][c]);
		
		anim = new Animation<TextureRegion>(animTime, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		
		setAnimation(anim);
	}
	
	public Shop getShop() {
		return shop;
	}
}