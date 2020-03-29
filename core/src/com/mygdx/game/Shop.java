package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Shop {
	
	private Table shop;
	private InventoryItemSlot bt[];
	
	private static final int itemMaxSlotWidth = 100;
	private static final int itemMaxSlotHeight = 100;
	private static final int itemMinSlotWidth = 60;
	private static final int itemMinSlotHeight = 60;
	
	public Shop(Table uiTable) {
		shop = new Table();
		
		bt = new InventoryItemSlot[3];
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new InventoryItemSlot();
			shop.add(bt[i]).minWidth(itemMinSlotWidth).minHeight(itemMinSlotHeight).maxWidth(itemMaxSlotWidth).maxHeight(itemMaxSlotHeight);
		}
		
		shop.setVisible(false);
		
		uiTable.add(shop);
		uiTable.row();
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