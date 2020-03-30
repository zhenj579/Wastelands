package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class EndScreen extends BaseScreen {
	
	
	protected Skin skin;
	private TextureAtlas atlas;

	@Override
	public void initialize() {
		atlas = new TextureAtlas("flat-earth-ui.atlas");
		skin = new Skin(Gdx.files.internal("flat-earth-ui.json"),atlas);
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.getPadTop();
		
		Label text = new Label("Thank you for playing the game, this is the end for now!", skin);
		Label text1 = new Label("Press any key to exit", skin);
		
		text.setFontScale(3.0f);
		text1.setFontScale(3.0f);
		
		mainTable.add(text);
		mainTable.row();
		mainTable.add(text1);
		
		super.mainStage.addActor(mainTable);
	}

	@Override
	public void update(float dt) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY))
		{
			Gdx.app.exit();
		}
		super.show();
	}

}
