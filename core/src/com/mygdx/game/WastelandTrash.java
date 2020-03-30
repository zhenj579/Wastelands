package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class WastelandTrash extends BaseActor{
	
	private boolean destroyed;
	private String name;
	private Map ref;
	int i = 0;
	private Sound sfx;
	private boolean vacAble = true;
	private Animation<TextureRegion> anim;
	
	public WastelandTrash(String name, float x, float y, Stage stage, Map ref, int index)
	{
		super(x,y,stage);
		this.name = name;
		this.setWidth(16);
		this.setHeight(16);
		destroyed = false;
		setBoundaryPolygon(8);
		super.setBoundaryRectangle();
		this.ref = ref;
		stage.addActor(this);	
		i = index;
//		this.setMaxSpeed(40);
//		this.setAcceleration(380);
//		this.setDeceleration(380);
		//initAnim();
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
		if(vacAble && LevelScreen.wastelander.isVacUpgrd() && this.isWithinDistance(LevelScreen.wastelander.getVacDistance(), LevelScreen.wastelander)) {
			Vector2 playerToActorVector = new Vector2(LevelScreen.wastelander.getX() - this.getX(), LevelScreen.wastelander.getY() - this.getY());
			this.accelerateAtAngle(playerToActorVector.angle());
			this.applyPhysics(dt);
		}
		//Gdx.app.log("TrashName: ", name);
	}
	
	public void setSFX(String path)
	{
		sfx = Gdx.audio.newSound(Gdx.files.internal(path));
	}
	
	public void playSFX()
	{
		sfx.play(1.0f);
	}
	
	public boolean isDestroyed()
	{
		return destroyed;
	}
	
	public void destroy()
	{
		destroyed = true;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public boolean remove() {
		super.remove();
		ref.remove((int)getX() / 16, (int)getY() / 16);
		return false;
	}
	
	public void initAnim() {
		Texture sprite = new Texture("trash_sprites.png");

		TextureRegion[][] tr = TextureRegion.split(sprite, 16, 16);	
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		
		if(name.equals("trashedCan")) {
			textureArray.add( tr[0][0]);
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
		if(name.equals("trashedBottle")) {
			textureArray.add( tr[0][1]);
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
		if(name.equals("brokenChair")) {
			textureArray.add( tr[0][2]);
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
		if(name.equals("trashedBook")) {
			textureArray.add( tr[0][3]);
			anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		}
		
		anim = new Animation<TextureRegion>(0.0f, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		
		setAnimation(anim);
	}
}
