package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InventoryButtonListener extends ClickListener{
	
	private Button bt;
	
	public InventoryButtonListener(Button bt) {
		this.bt = bt;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if(bt.getColor().equals(Color.CYAN)) {
			bt.setColor(Color.WHITE);
		} else {
			bt.setColor(Color.CYAN);
		}
	}
	
}
