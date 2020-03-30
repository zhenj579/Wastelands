package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class InventoryItemSlot extends Button{
	
	protected InventoryItem item;
	private int quantity = 0;
	protected Label quantityLabel;
	protected boolean hidden = false;
	
	private static final int labelOffSetX = 8;
	private static final int labelOffSetY = -30;
	
	private boolean isShopSlot = false;
	
	public InventoryItemSlot() {
		super(BaseGame.skin);
		quantityLabel = new Label(quantity + "", BaseGame.labelStyle);
		quantityLabel.setFontScale(0.5f);
		quantityLabel.setVisible(false);
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
		if(item != null && quantity > 1) {
			quantityLabel.setText(quantity + "");
			quantityLabel.setVisible(true);
		} else {
			quantityLabel.setVisible(false);
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(item != null && !hidden) {
			batch.draw(item.itemTextureRegion, getX() + getWidth()/2 - 16, getY() + getHeight()/2 - 16);
			if(quantity > 0 && !isShopSlot) {
				quantityLabel.setX(getX() + getWidth()/2 + labelOffSetX);
				quantityLabel.setY(getY() + getHeight()/2 + labelOffSetY);
				quantityLabel.draw(batch, parentAlpha);
			}
		}
		super.validate();
	}
	
	public void updateLabel() {
		quantityLabel.setText(quantity + "");
	}
	
	public void clearSlot() {
		item = null;
		quantity = 0;
	}
	
	public boolean isEmpty() {
		if(item == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setShopSlot(boolean slot) {
		isShopSlot = slot;
	}
	
	public boolean isShopSlot() {
		return isShopSlot;
	}
	
	public void setItem(InventoryItem item) {
		if(this.item != null && this.item.equals(item)) {
			incrementQuantity();
			updateLabel();
		} else {
			this.item = item;
			incrementQuantity();
			updateLabel();
		}
	}
	
	public void setItem(InventoryItem item, int q) {
		this.item = item;
		this.quantity = q;
		updateLabel();
	}
	
	public void setQuantity(int q) {
		this.quantity = q;
		updateLabel();
	}
	
	public void incrementQuantity() {
		quantity++;
		updateLabel();
	}
	
	public void decrementQuantity() {
		quantity--;
		if(quantity == 0) {
			this.clearSlot();
		}
		updateLabel();
	}
	
	public void decrementQuantityBy(int amount) {
		quantity -= amount;
		if(quantity == 0) {
			this.clearSlot();
		}
		updateLabel();
	}
	
	public InventoryItem getItem() {
		return item;
	}
	
	public int getQuanity() {
		return quantity;
	}
	
	public void hideItem() {
		hidden = true;
	}
	
	public void showItem() {
		hidden = false;
	}
	
	public void increaseQuantityBy(int amount) {
		quantity += amount;
		updateLabel();
	}
	
	public InventoryItemSlot getItemSlotCopy() {
		InventoryItemSlot copy = new InventoryItemSlot();
		copy.setItem(item);
		copy.setQuantity(quantity);
		return copy;
	}
}