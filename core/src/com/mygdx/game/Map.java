package com.mygdx.game;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Map extends Actor {
	
	public static final float V_WIDTH = 67;
	public static final float V_HEIGHT = 40;
	public static final float PPM = 16;
	public static float zoom = 0.5f;
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera cam;
	private HashMap<Vector2, WastelandTrash> mapObjects;
	private int mapWidth;
	private int mapHeight;
	private int tileWidth;
	private int tileHeight;
	private int numTilesHorizontal;
	private int numTilesVertical;
	
	public Map(Stage stage)
	{
		map = new TmxMapLoader().load("wasteland_test_map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		cam = new OrthographicCamera();
		mapObjects = new HashMap<>();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
		tileWidth = (int)map.getProperties().get("tilewidth");
		tileHeight = (int)map.getProperties().get("tileheight");
		numTilesHorizontal = (int)map.getProperties().get("width");
		numTilesVertical = (int)map.getProperties().get("height");
		mapWidth = tileWidth * numTilesHorizontal;
		mapHeight = tileHeight * numTilesVertical;
		
		BaseActor.setWorldBounds(mapWidth, mapHeight);
		
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0); 
		
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			WastelandTrash trash = new WastelandTrash(object.getName(),(int)rect.getX(),(int)rect.getY(),stage);
			mapObjects.put(new Vector2((int)rect.getX(),(int)rect.getY()), trash);
		}
			
		
		stage.addActor(this);
	}
	
	public WastelandTrash getTrashAt(Vector2 coordinates)
	{
		for(Entry<Vector2, WastelandTrash> t : mapObjects.entrySet())
		{
			Vector2 entry = t.getKey();
			if(coordinates.x >= entry.x - 8 && coordinates.x <= entry.x + 8
					&& coordinates.y >= entry.y - 8 && coordinates.y <= entry.y + 8)
			{
				return t.getValue();
			}
		}
		return null;
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
