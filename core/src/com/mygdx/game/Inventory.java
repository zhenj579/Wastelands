package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class Inventory extends Actor{
	
	private int nRows;
	private int nCols;
	private Table uiTable;
	private InventoryItemSlot [][] bts;
	
	private Table inventory = new Table();
 
	private InventoryItemSlot curSelected;
	private InventoryItemSlot lastHoverOver;
	
	private boolean dragging = false;
	
	public Inventory(int nRows, int nCols, Table uiTable) {
		this.nRows = nRows;
		this.nCols = nCols;
		this.uiTable = uiTable;
		BaseScreen.uiStage.addActor(this);
		bts = new InventoryItemSlot[nRows][nCols];
		inventory.align(Align.center);
		initBts();
		uiTable.add(inventory);
		inventory.setVisible(false);
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
		if(dragging && !inventory.isVisible()) {
			dragging = false;
			curSelected.showItem();
			curSelected = null;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(dragging && curSelected != null && !curSelected.isEmpty()) {
			curSelected.hideItem();
			Vector2 screenpos = BaseScreen.uiStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
			batch.draw(curSelected.getItem().getTextureRegion(), screenpos.x - 22, screenpos.y - 4);
		} else if(curSelected != null){
			curSelected.showItem();
		}
	}
	
	private void initBts() {
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				bts[r][c] = new InventoryItemSlot(BaseGame.textButtonStyle);
				bts[r][c].addListener(new InventoryButtonListener(bts[r][c], this));
				bts[r][c].addListener(new InventoryDragListener(bts[r][c], this));
				inventory.add(bts[r][c]);
			}
			inventory.row();
		}
	}
	
	public boolean isVisible() {
		return inventory.isVisible();
	}
	
	public void setVisible(boolean visibility) {
		inventory.setVisible(visibility);
	}
	
	public void addItem(InventoryItem item, int quantity) {
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				if(bts[r][c].isEmpty()) {
					bts[r][c].setItem(item);
				} else if(bts[r][c].getItem().equals(item)) {
					bts[r][c].incrementQuantity();
				}
			}
		}
			
	}
	
	public void moveItem(InventoryItemSlot source, InventoryItemSlot destination) {
		if(source == null || destination == null || (source == destination)) {
			return;
		}
		destination.setItem(source.getItem(), source.getQuanity());
		source.clearSlot();
	}
	
	public void setCurSel(InventoryItemSlot bt) {
		curSelected = bt;
	}
	
	public void setLastHoveredOver(InventoryItemSlot bt) {
		lastHoverOver = bt;
	}
	
	public InventoryItemSlot getLastHoveredOver() {
		return lastHoverOver;
	}
	
	public InventoryItemSlot getCurSel() {
		return curSelected;
	}
	
	public Table getInvTable() {
		return inventory;
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	public void setDragging(boolean drag) {
		dragging = drag;
	}
}