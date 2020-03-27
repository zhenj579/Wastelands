package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
	
	public static final float V_WIDTH = 67;
	public static final float V_HEIGHT = 40;
	public static final float PPM = 16;
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera cam;
	
	public Map()
	{
		map = new TmxMapLoader().load("wasteland_test_map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1f/PPM);
		cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		cam.position.set(V_WIDTH/2, V_HEIGHT/2, 0);
		cam.update();
		renderer.setView(cam);
	}
	
	public void render()
	{
		renderer.render();
	}
	
	

}
