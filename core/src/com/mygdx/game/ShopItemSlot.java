package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public class ShopItemSlot extends InventoryItemSlot {
	
	private int canPrice = 0;
	private int bottlePrice = 0;
	private int paperPrice = 0;
	private int woodPrice = 0;
	
	private boolean isBack = false;
	private boolean isSpd = false;
	private boolean isVac = false;
	
	private String effect;

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(item != null && !hidden) {
			batch.draw(item.itemTextureRegion, getX() + getWidth() / 2 - 16, getY() + getHeight() / 2 - 16);
		}
		super.validate();
	}
	
	public boolean isBack() {
		return isBack;
	}
	
	public void setBack(boolean isBck) {
		effect = "back";
		this.isBack = isBck;
	}
	
	
	public boolean isSpd() {
		return isSpd;
	}
	
	
	public void setSpd(boolean isSpd) {
		effect = "speed";
		this.isSpd = isSpd;
	}
	
	
	public boolean isVac() {
		return isVac;
	}
	
	
	public void setVac(boolean isVac) {
		effect = "vac";
		this.isVac = isVac;
	}
	
	public boolean playerHasEffect() {
		if(isBack && !isSpd && !isVac) {
			return LevelScreen.wastelander.isInvUpgrd();
		}
		if(!isBack && isSpd && !isVac) {
			return LevelScreen.wastelander.isSpdUpgrd();
		}
		if(!isBack && !isSpd && isVac) {
			return LevelScreen.wastelander.hasVacEffect();
		}
		return false;
	}
	
	public ShopItemSlot() {
		super();
		super.setShopSlot(true);
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
	
	public void activateEffect() {
		if(isBack && !isSpd && !isVac) {
			LevelScreen.wastelander.activateBackEffect();
		}
		if(!isBack && isSpd && !isVac) {
			LevelScreen.wastelander.activateSpeedEffect();
		}
		if(!isBack && !isSpd && isVac) {
			LevelScreen.wastelander.activateVacEffect();
		}
	}
}