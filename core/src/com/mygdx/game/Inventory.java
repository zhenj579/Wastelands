package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Inventory {
	
	private int nRows;
	private int nCols;
	Table uiTable;
	Button [][] bts;
	
	Table inventory = new Table();
	
	public Inventory(int nRows, int nCols, Table uiTable) {
		this.nRows = nRows;
		this.nCols = nCols;
		this.uiTable = uiTable;
		bts = new Button[nRows][nCols];
		initBts();
		uiTable.add(inventory);
		inventory.setVisible(false);
	}
	
	private void initBts() {
		for(int r = 0; r < nRows; r++) {
			for(int c = 0; c < nCols; c++) {
				bts[r][c] = new Button(BaseGame.textButtonStyle);
				bts[r][c].setColor(Color.CYAN);
				bts[r][c].addListener(new InventoryButtonListener(bts[r][c]));
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
}
