package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Inventory extends Actor{
	
	private int nRows;
	private int nCols;
	private InventoryItemSlot [][] bts;
	
	protected Table inventory = new Table();
 
	private InventoryItemSlot curSelected;
	private InventoryItemSlot lastHoverOver;
	
	private boolean dragging = false;
	
	private static final int mouseDragXOffSet = 22;
	private static final int mouseDragYOffSet = 4;
	private static final int itemMaxSlotWidth = 100;
	private static final int itemMaxSlotHeight = 100;
	private static final int itemMinSlotWidth = 60;
	private static final int itemMinSlotHeight = 60;
	
	private int nCans = 0;
	private int nBottles = 0;
	private int nPapers = 0;
	private int nWood = 0;
	
	private boolean allowStacking = false;

	public Inventory(int nRows, int nCols, Table uiTable) {
		this.nRows = nRows;
		this.nCols = nCols;
		BaseScreen.uiStage.addActor(this);
		bts = new InventoryItemSlot[nRows][nCols];
		//inventory.align(Align.top|Align.center);
		initUI(uiTable);
		initBts();
		uiTable.add(inventory);
		inventory.setVisible(false);
		
	}
	
	private void initUI(Table uiTable) {
		uiTable.row().growY();
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
			batch.draw(curSelected.getItem().getTextureRegion(), screenpos.x - mouseDragXOffSet, screenpos.y - mouseDragYOffSet);
		} else if(curSelected != null){
			curSelected.showItem();
		}
	}
	
	private void initBts() {
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				bts[r][c] = new InventoryItemSlot();
				bts[r][c].addListener(new InventoryButtonListener(bts[r][c], this));
				bts[r][c].addListener(new InventoryDragListener(bts[r][c], this));
				inventory.add(bts[r][c]).minWidth(itemMinSlotWidth).minHeight(itemMinSlotHeight).maxWidth(itemMaxSlotWidth).maxHeight(itemMaxSlotHeight);
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
	
	public void addItem(InventoryItem item, int amount) {
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				if(bts[r][c].getItem() != null && bts[r][c].getItem().equals(item) && allowStacking) {
					bts[r][c].increaseQuantityBy(amount);
					return;
				}
			}
		}
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				if(bts[r][c].isEmpty()) {
					bts[r][c].setItem(item);
					bts[r][c].increaseQuantityBy(amount);
					if(item.getName().equals("trashedBottle")) {
						nBottles++;
					} else if(item.getName().equals("trashedBook")) {
						nPapers++;
					} else if(item.getName().equals("trashedCan")) {
						nCans++;
					} else if(item.getName().equals("trashedWood")) {
						nWood++;
					}
					return;
				} else if(bts[r][c].getItem().equals(item)  && allowStacking) {
					bts[r][c].incrementQuantity();
					return;
				}
			}
		}
			
	}
	
	public void addItem(InventoryItem item) {
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				if(bts[r][c].getItem() != null && bts[r][c].getItem().equals(item) && allowStacking) {
					bts[r][c].incrementQuantity();
					return;
				}
			}
		}
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				if(bts[r][c].isEmpty()) {
					bts[r][c].setItem(item);
					if(item.getName().equals("trashedBottle")) {
						nBottles++;
					} else if(item.getName().equals("trashedBook")) {
						nPapers++;
					} else if(item.getName().equals("trashedCan")) {
						nCans++;
					} else if(item.getName().equals("trashedWood")) {
						nWood++;
					}
					return;
				} else if(bts[r][c].getItem().equals(item) && allowStacking) {
					bts[r][c].incrementQuantity();
					return;
				}
			}
		}
	}
	
	public void moveItem(InventoryItemSlot source, InventoryItemSlot destination) {
		if(source == null || destination == null || (source == destination)) {
			return;
		}
		if(!destination.isEmpty()) {
			if(destination.getItem().equals(source.getItem()) && allowStacking) {
				destination.increaseQuantityBy(source.getQuanity());
				source.clearSlot();
				return;
			}
			InventoryItemSlot temp = destination.getItemSlotCopy();
			destination.setItem(source.getItem(), source.getQuanity());
			source.setItem(temp.getItem(), temp.getQuanity());
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
	
	public boolean isAllowStacking() {
		return allowStacking;
	}

	public void setAllowStacking(boolean allowStacking) {
		this.allowStacking = allowStacking;
	}
	
	public boolean canAfford(int can, int bottle, int paper, int wood) {
		return nCans >= can && nBottles >= bottle && nPapers >= paper && nWood >= wood;
	}
	
	public void deduct(int cans, int bottles, int paper, int wood) {
		boolean cansDone = false, bottlesDone = false, paperDone = false, woodDone = false;
		for(InventoryItemSlot[] e : bts) {
			for(InventoryItemSlot f : e) {
				if(!cansDone) {
					if(f.getItem() != null && f.getItem().getName().equals("trashedCan")) {
						if(f.getQuanity() >= cans) {
							f.decrementQuantityBy(cans);
							cansDone = true;
						} else {
							cans -= f.getQuanity();
							f.clearSlot();
						}
					}
				}
				if(!paperDone) {
					if(f.getItem() != null && f.getItem().getName().equals("trashedBook")) {
						if(f.getQuanity() >= paper) {
							f.decrementQuantityBy(paper);
							paperDone = true;
						} else {
							paper -= f.getQuanity();
							f.clearSlot();
						}
					}
				}
				if(!bottlesDone) {
					if(f.getItem() != null && f.getItem().getName().equals("trashedBottle")) {
						if(f.getQuanity() >= bottles) {
							f.decrementQuantityBy(bottles);
							bottlesDone = true;
						} else {
							bottles -= f.getQuanity();
							f.clearSlot();
						}
					}
				}
				if(!woodDone) {
					if(f.getItem() != null && f.getItem().getName().equals("trashedWood")) {
						if(f.getQuanity() >= wood) {
							f.decrementQuantityBy(wood);
							woodDone = true;
						} else {
							wood -= f.getQuanity();
							f.clearSlot();
						}
					}
				}
				f.updateLabel();
				if(f.getItem() != null)
					Gdx.app.log("Deduct", f.getItem().getName() + " " + f.getQuanity());
			}
		}
	}
}