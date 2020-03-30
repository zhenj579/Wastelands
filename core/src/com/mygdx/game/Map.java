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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Map extends Actor {
	
	public static final float V_WIDTH = 67;
	public static final float V_HEIGHT = 40;
	public static final float PPM = 16;
	public static float zoom = 0.4f;
	
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
	
	public Map(String path, Stage stage)
	{
		map = new TmxMapLoader().load(path);
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
		
		int i = 0;
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			WastelandTrash trash = new WastelandTrash(object.getName(),(int)rect.getX(),(int)rect.getY(),stage, this, i);
			if(trash.getName().equals("trashedBottle"))
			{
				trash.setSFX("bottle_pickup.mp3");
			}
			else if(trash.getName().equals("trashedBook"))
			{
				trash.setSFX("paper_pickup.mp3");
			}
			else if(trash.getName().equals("trashedCan"))
			{
				trash.setSFX("can_pickup.mp3");
			}
			else
			{
				trash.setSFX("wood_pickup.mp3");
			}
			mapObjects.put(new Vector2((int)rect.getX(),(int)rect.getY()), trash);
			i++;
		}
			
		
		stage.addActor(this);
	}
	
	public WastelandTrash getTrashAt(final Vector2 coordinates, final Player player)
	{
		for(final Entry<Vector2, WastelandTrash> t : mapObjects.entrySet())
		{
			final Vector2 entry = t.getKey();
			if(t.getValue().isDestroyed())
			{
				t.getValue().addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y)
					{
						Vector2 screenToStage = BaseScreen.mainStage.screenToStageCoordinates(new Vector2(x, y));
						if(t.getValue().isDestroyed() && player.isWithinDistance(34, t.getValue()))
						{
							InventoryItem item;
							if(t.getValue().getName().equals("trashedBottle"))
							{
								item = new InventoryItem("trashedBottle", "bottles.png");
							}
							else if(t.getValue().getName().equals("trashedBook"))
							{
								item = new InventoryItem("trashedBook", "paper.png");
							}
							else if(t.getValue().getName().equals("trashedCan"))
							{
								item = new InventoryItem("trashedCan", "cans.png");
							}
							else
							{
								item = new InventoryItem("trashedWood", "wood.png");
							}
							player.getInventory().addItem(item);
							t.getValue().remove();
							t.getValue().playSFX();
						}
					}
				});
				mapObjects.remove(entry);
				return null;
			}
			else if(coordinates.x >= entry.x - 16 && coordinates.x <= entry.x + 16
					&& coordinates.y >= entry.y - 16 && coordinates.y <= entry.y + 16)
			{

				t.getValue().destroy();
				return t.getValue();
			}
		}
		return null;
	}
	
	
	 public void act(float dt)
	 {
		 super.act( dt );
	 } 
	 
	 public void remove(int x, int y) {
		TiledMapTileLayer layer =  (TiledMapTileLayer) map.getLayers().get(1);
		layer.getCell(x, y).setTile(null);
	 }
	 
	 public void printObjectState()
	 {
			for(Entry<Vector2, WastelandTrash> t : mapObjects.entrySet())
			{
				System.out.println(t.getKey() + " " + t.getValue());
			}
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

	
	public boolean isEmpty()
	{
		return mapObjects.isEmpty();
	}

}
