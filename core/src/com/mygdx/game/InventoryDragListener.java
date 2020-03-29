package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class InventoryDragListener extends DragListener {
	
	private InventoryItemSlot bt;
	private Inventory ref;
	
	public InventoryDragListener(InventoryItemSlot bt, Inventory ref) {
		this.ref = ref;
		this.bt = bt;
	}
	
	@Override
	public void dragStart(InputEvent event, float x, float y, int pointer) {
		ref.setCurSel(bt);
		ref.setDragging(true);;
	}
	
	public void dragStop(InputEvent event, float x, float y, int pointer) {
		ref.moveItem(ref.getCurSel(), ref.getLastHoveredOver());
		ref.setDragging(false);
	}
}
