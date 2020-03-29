package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public class ShopItemSlot extends InventoryItemSlot {
	
	private int canPrice = 0;
	private int bottlePrice = 0;
	private int paperPrice = 0;
	private int woodPrice = 0;
	
	public ShopItemSlot() {
		super();
		super.setShopSlot(true);
	}
	

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(item != null && !hidden) {
			batch.draw(item.itemTextureRegion, getX() + getWidth() / 2 - 16, getY() + getHeight() / 2 - 16);
		}
		super.validate();
	}
	
	public void setPrice(int can, int bottle, int paper, int wood) {
		canPrice = can;
		bottlePrice = bottle;
		paperPrice = paper;
		woodPrice = wood;
	}
	
	public int getCanPrice() {
		return canPrice;
	}
	
	public void setCanPrice(int canPrice) {
		this.canPrice = canPrice;
	}
	
	public int getBottlePrice() {
		return bottlePrice;
	}
	
	public void setBottlePrice(int bottlePrice) {
		this.bottlePrice = bottlePrice;
	}
	
	public int getPaperPrice() {
		return paperPrice;
	}
	
	public void setPaperPrice(int paperPrice) {
		this.paperPrice = paperPrice;
	}
	
	public int getWoodPrice() {
		return woodPrice;
	}
	
	public void setWoodPrice(int woodPrice) {
		this.woodPrice = woodPrice;
	}
}