package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Map extends Actor {
	
	public static final float V_WIDTH = 67;
	public static final float V_HEIGHT = 40;
	public static final float PPM = 16;
	public static float zoom = 0.4f;
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera cam;
	
	
	public Map(Stage stage)
	{
		map = new TmxMapLoader().load("wasteland_test_map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
		int tileWidth = (int)map.getProperties().get("tilewidth");
		int tileHeight = (int)map.getProperties().get("tileheight");
		int numTilesHorizontal = (int)map.getProperties().get("width");
		int numTilesVertical = (int)map.getProperties().get("height");
		int mapWidth = tileWidth * numTilesHorizontal;
		int mapHeight = tileHeight * numTilesVertical;
		
		BaseActor.setWorldBounds(mapWidth, mapHeight);
		
		stage.addActor(this);
	}
	
	 public void act(float dt)
	 {
		 super.act( dt );
	 } 
	 
	public void draw(Batch batch, float parentAlpha)
	{
		Camera mainCamera = getStage().getCamera();
		cam.position.x = mainCamera.position.x;
		cam.position.y = mainCamera.position.y;
		((OrthographicCamera)mainCamera).zoom = zoom;
		cam.zoom = zoom;
		cam.update();
		renderer.setView(cam);
		
		batch.end();
		renderer.render();
		batch.begin();
		
	}
	
	

}
