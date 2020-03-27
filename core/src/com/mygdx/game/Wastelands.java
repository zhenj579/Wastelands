package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Wastelands extends ApplicationAdapter {
	
	Map map;
	
	@Override
	public void create () {
		//map = new Map();	
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//map.render();

	}
	
	@Override
	public void dispose () {

	}
}
