package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InventoryButtonListener extends ClickListener{
	
	private InventoryItemSlot bt;
	private Inventory ref;
	private static boolean added = false;
	
	public InventoryButtonListener(InventoryItemSlot bt, Inventory ref) {
		this.bt = bt;
		this.ref = ref;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if(bt.isEmpty() && !added) {
//			bt.setItem(new InventoryWaterBottle(), 3);
		} 
	}
	
	@Override
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		if(ref.isDragging()) {
			ref.setLastHoveredOver(bt);
		}
	}
	
	@Override
	public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		if(ref.isDragging()) {
			ref.setLastHoveredOver(null);
		}
	}
}