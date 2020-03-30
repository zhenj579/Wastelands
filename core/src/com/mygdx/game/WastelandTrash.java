package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

public class WastelandTrash extends BaseActor{
	
	private boolean destroyed;
	private String name;
	private Map ref;
	int i = 0;
	private Sound sfx;
	private boolean vacAble = true;
	
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

	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
		if(vacAble && LevelScreen.wastelander.isVacUpgrd() && this.isWithinDistance(LevelScreen.wastelander.getVacDistance(), LevelScreen.wastelander)) {
			VacTrashActor actor = new VacTrashActor(this.getX(), this.getY(), BaseScreen.mainStage, name);
//			Timer.schedule(new Timer.Task() {
//				
//				@Override
//				public void run() {
//					if()
//				}a
//				
//			}, delaySeconds, intervalSeconds, repeatCount);
			this.destroy();
			this.remove();
		}
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
	
}
