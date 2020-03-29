package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InventoryItem {
	
	protected Texture itemTexture;
	protected TextureRegion itemTextureRegion;
	protected String path;
	protected String itemName;
	
	public InventoryItem(String texturePath) {
		itemTexture = new Texture(texturePath);
		itemTextureRegion = new TextureRegion(itemTexture);
		this.path = texturePath;
	}
	
	public void setName(String name)
	{
		this.itemName = name;
	}
	
	public String getName()
	{
		return itemName;
	}
	
	public TextureRegion getTextureRegion() {
		return itemTextureRegion;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof InventoryItem)){
			return false;
		}
		return path.equals(((InventoryItem)other).path);
	}
}
