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
//		if(!ref.moveItem(ref.getCurSel(), ref.getLastHoveredOver()) && ref.getCurSel() != null) {
//			//LevelScreen.mainStage.addActor(new Image("wood_world.png", LevelScreen.wastelander.getX(), LevelScreen.wastelander.getY(), BaseScreen.mainStage));
//			VacTrashActor vat = new VacTrashActor(LevelScreen.wastelander.getX(), LevelScreen.wastelander.getY(), BaseScreen.mainStage, ref.getCurSel().getItem().getName());
//			vat.addListener(new VacTrashActorListener(ref.getCurSel().getItem().getName()));
//			LevelScreen.trashGroup.addActor(vat);
//			LevelScreen.wastelander.getInventory().deleteCurSelected();
//		}
		ref.moveItem(ref.getCurSel(), ref.getLastHoveredOver());
		ref.setDragging(false);
	}
}
