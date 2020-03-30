package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WastelandTrash extends BaseActor{
	
	private boolean destroyed;
	private String name;
	private Map ref;
	int i = 0;
	private Sound sfx;
	
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
