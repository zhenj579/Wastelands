package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class VacTrashActor extends BaseActor{
	
	private String name;
	private String itemName;
	private String itemTexture;
	private Animation<TextureRegion> anim;
	private boolean vacAble = true;
	private WastelandTrash wt;
	
	public VacTrashActor(float x, float y, Stage s, String name, WastelandTrash wt) {
		super(x, y, s);
		this.name = name;
		this.wt = wt;
		
		if(name.equals("trashedCan")) {
			itemTexture = "cans.png";
			itemName = "trashedCan";
		}
		if(name.equals("trashedBottle")) {
			itemTexture = "bottles.png";
			itemName = "trashedBottle";
		}
		if(name.equals("brokenChair")) {
			itemTexture = "wood.png";
			itemName = "trashedWood";
		}
		if(name.equals("trashedBook")) {
			itemTexture = "paper.png";
			itemName = "trashedBook";
		}
		
		this.setWidth(16);
		this.setHeight(16);
		
		this.setMaxSpeed(LevelScreen.wastelander.getVacMaxSpeed());
		this.setAcceleration(LevelScreen.wastelander.getVacAccel());
		this.setDeceleration(LevelScreen.wastelander.getVacDecel());
		
		initAnim();
	}
	
	public void act(float dt) {
		super.act(dt);
		if(vacAble && LevelScreen.wastelander.isVacUpgrd() && LevelScreen.wastelander.isWithinDistance(LevelScreen.wastelander.getVacDistance(), this)) {
			Vector2 playerToActorVector = new Vector2(LevelScreen.wastelander.getX() - this.getX(), LevelScreen.wastelander.getY() - this.getY());
			this.accelerateAtAngle(playerToActorVector.angle());
			this.applyPhysics(dt);
		}
		if(this.overlaps(LevelScreen.wastelander)) {
			if(!LevelScreen.wastelander.getInventory().isFull() && LevelScreen.wastelander.getInventory().hasSpace(itemName)) {
				LevelScreen.wastelander.getInventory().addItem(new InventoryItem(itemName, itemTexture));
				this.remove();
				wt.destroy();
			}
		}
		setAnimation(anim);
	}
	
	public void initAnim() {
		if(name.equals("trashedCan")) {
			Texture sprite = new Texture("can_world.png");
			TextureRegion[][] tr = TextureRegion.split(sprite, 16, 16);
			
			Array<TextureRegion> textureArray = new Array<TextureRegion>();
			
			for (int c = 0; c < 1; c++)
				textureArray.add( tr[0][c]);
			
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
		if(name.equals("trashedBottle")) {
			Texture sprite = new Texture("bottle_world.png");
			TextureRegion[][] tr = TextureRegion.split(sprite, 16, 16);
			
			Array<TextureRegion> textureArray = new Array<TextureRegion>();
			
			for (int c = 0; c < 1; c++)
				textureArray.add( tr[0][c]);
			
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
		if(name.equals("brokenChair")) {
			Texture sprite = new Texture("wood_world.png");
			TextureRegion[][] tr = TextureRegion.split(sprite, 16, 16);
			
			Array<TextureRegion> textureArray = new Array<TextureRegion>();
			
			for (int c = 0; c < 1; c++)
				textureArray.add( tr[0][c]);
			
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
		if(name.equals("trashedBook")) {
			Texture sprite = new Texture("paper_world.png");
			TextureRegion[][] tr = TextureRegion.split(sprite, 16, 16);
			
			Array<TextureRegion> textureArray = new Array<TextureRegion>();
			
			for (int c = 0; c < 1; c++)
				textureArray.add( tr[0][c]);
			
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
			
		setAnimation(anim);
	}
	
}
