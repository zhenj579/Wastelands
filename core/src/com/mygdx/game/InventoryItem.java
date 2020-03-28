package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public abstract class InventoryItem {
	
	protected Texture itemTexture;
	protected TextureRegion itemTextureRegion;
	
	public InventoryItem(String texturePath) {
		itemTexture = new Texture(texturePath);
		itemTextureRegion = new TextureRegion(itemTexture);
	}
	
	public TextureRegion GetTextureRegion() {
		return itemTextureRegion;
	}
}
