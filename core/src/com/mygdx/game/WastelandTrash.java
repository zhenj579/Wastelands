package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class WastelandTrash extends BaseActor {
	
	private boolean destroyed;
	private String name;
	
	public WastelandTrash(String name, float x, float y, Stage stage)
	{
		super(x,y,stage);
		this.name = name;
		destroyed = false;
		super.setBoundaryRectangle();
		stage.addActor(this);
		
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
	
	

}
