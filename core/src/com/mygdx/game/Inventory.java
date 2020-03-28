package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Inventory {
	
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
		bts = new InventoryItemSlot[nRows][nCols];
		initBts();
		uiTable.add(inventory);
		inventory.setVisible(false);
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
				}
			}
		}
			
	}
	
	public void moveItem(InventoryItemSlot source, InventoryItemSlot destination) {
		if(source == null || destination == null) {
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