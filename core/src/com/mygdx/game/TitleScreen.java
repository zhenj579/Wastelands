package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TitleScreen extends BaseScreen {
	
	private Game game;
	protected Skin skin;
	private TextureAtlas atlas;


	
	public TitleScreen(Game game)
	{
		this.game = game;
	}
	
	@Override
	public void initialize() {
		atlas = new TextureAtlas("flat-earth-ui.atlas");
		skin = new Skin(Gdx.files.internal("flat-earth-ui.json"),atlas);
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.getPadTop();
		
		Label text = new Label("Hi, controls are as follows: WASD to move, left click while you're standing on top of the trash to collect it.", skin);
		Label text2 = new Label("Left click on the npc to interact with it and press E to open your inventory", skin);
		Label text3 = new Label("As you collect more trash, you can trade it with the npc to unlock upgrades such as: ", skin);
		Label text4 = new Label("speed upgrades, inventory upgrades, and vacuum upgrade.", skin);
		Label text5 = new Label("speed upgrade = increase speed", skin);
		Label text6 = new Label("inventory upgrade = allows for stacking of items",skin);
		Label text7 = new Label("vacuum upgrade = sucks trash within a radius without you having to click", skin);
		Label text8 = new Label(" Clear the map of trash to win!", skin);
		Label text9 = new Label("Press any key to continue", skin);
		
		text.setFontScale(2.5f);
		text2.setFontScale(2.5f);
		text3.setFontScale(2.5f);
		text4.setFontScale(2.5f);
		text5.setFontScale(2.5f);
		text6.setFontScale(2.5f);
		text7.setFontScale(2.5f);
		text8.setFontScale(2.5f);
		text9.setFontScale(2.5f);
		
		mainTable.add(text);
		mainTable.row();
		mainTable.add(text2);
		mainTable.row();
		mainTable.add(text3);
		mainTable.row();
		mainTable.add(text4);
		mainTable.row();
		mainTable.add(text5);
		mainTable.row();
		mainTable.add(text6);
		mainTable.row();
		mainTable.add(text7);
		mainTable.row();
		mainTable.add(text8);
		mainTable.row();
		mainTable.add(text9);
		
		mainStage.addActor(mainTable);
	}

	@Override
	public void update(float dt) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
		{
			game.setScreen(new LevelScreen(game));
			super.dispose();
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.show();
		

		
	}

	
	

}
