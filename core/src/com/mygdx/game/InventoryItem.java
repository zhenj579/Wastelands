package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public abstract class InventoryItem {
	
	protected Texture itemTexture;
	protected TextureRegion itemTextureRegion;
	protected String path;
	protected String itemName;
	
	public InventoryItem(String texturePath, String itemName) {
		itemTexture = new Texture(texturePath);
		itemTextureRegion = new TextureRegion(itemTexture);
		this.path = texturePath;
		this.itemName = itemName;
	}
	
	public TextureRegion getTextureRegion() {
		return itemTextureRegion;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof InventoryItem)){
			return false;
		}
		return path.equals(((InventoryItem)other).path)
				&& itemName.equals(((InventoryItem)other).itemName);
	}
}
