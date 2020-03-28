package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InventoryButtonListener extends ClickListener{
	
	private InventoryItemSlot bt;
	
	public InventoryButtonListener(InventoryItemSlot bt) {
		this.bt = bt;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if(bt.isEmpty())
			bt.setItem(new InventoryWaterBottle());
		else
			bt.incrementQuantity();
	}
	
}
