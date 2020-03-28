package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class InventoryItemSlot extends Button{
	
	private InventoryItem item;
	private int quantity = 0;
	Label quantityLabel;
	
	public InventoryItemSlot(ButtonStyle bs) {
		super(bs);
		quantityLabel = new Label(quantity + "", BaseGame.labelStyle);
		quantityLabel.setFontScale(0.5f);
		quantityLabel.setVisible(false);
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
		if(item != null && quantity > 1) {
			quantityLabel.setText(quantity + "");;
			quantityLabel.setVisible(true);
		} else {
			quantityLabel.setVisible(false);
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(item != null) {
			batch.draw(item.itemTextureRegion, getX() + getWidth()/2 - 16, getY() + getHeight()/2 - 16);
			if(quantity > 0) {
				quantityLabel.setX(getX() + getWidth()/2 + 8);
				quantityLabel.setY(getY() + getHeight()/2 - 30);
				quantityLabel.draw(batch, parentAlpha);
			}
		}
		super.validate();
	}
	
	public boolean isEmpty() {
		if(item == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setItem(InventoryItem item) {
		this.item = item;
		quantity++;
	}
	
	public void setItem(InventoryItem item, int q) {
		this.item = item;
		this.quantity = q;
	}
	
	public void setQuantity(int q) {
		this.quantity = q;
	}
	
	public void incrementQuantity() {
		quantity++;
	}
	
	public InventoryItem GetItem() {
		return item;
	}
	
	public int getQuanity() {
		return quantity;
	}
}