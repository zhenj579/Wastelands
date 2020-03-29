package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Shop extends Actor {
	
	private Table shop;
	private ShopItemSlot bt[];
	
	private static final int itemMaxSlotWidth = 100;
	private static final int itemMaxSlotHeight = 100;
	private static final int itemMinSlotWidth = 60;
	private static final int itemMinSlotHeight = 60;
	
	public Shop(Table uiTable) {
		shop = new Table();
		
		bt = new ShopItemSlot[3];
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new ShopItemSlot();
			if(i == 0) {
				bt[i].setItem(new InventoryItem("backpack_pwr.png"));
				bt[i].setPrice(1, 1, 1, 1);
				bt[i].addListener(new ShopItemListener(bt[i]));
			}
			if(i == 1) {
				bt[i].setItem(new InventoryItem("speedup_pwr.png"));
				bt[i].setPrice(2, 2, 2, 2);
				bt[i].addListener(new ShopItemListener(bt[i]));
			}
			if(i == 2) {
				bt[i].setItem(new InventoryItem("vac_pwr.png"));
				bt[i].setPrice(3, 3, 3, 3);
				bt[i].addListener(new ShopItemListener(bt[i]));
			}
			shop.add(bt[i]).minWidth(itemMinSlotWidth).minHeight(itemMinSlotHeight).maxWidth(itemMaxSlotWidth).maxHeight(itemMaxSlotHeight);
		}
		
		shop.setVisible(false);
		
		uiTable.add(shop);
		uiTable.row();
		
		BaseScreen.uiStage.addActor(this);
	}
	
	private void initShop() {
		
	}

	public boolean isVisible() {
		return shop.isVisible();
	}
	
	public void setVisible(boolean visibility) {
		shop.setVisible(visibility);
	}
}