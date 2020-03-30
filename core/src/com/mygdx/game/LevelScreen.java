package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class LevelScreen extends BaseScreen
{
    private Map map;
    private Npc merchant;
	public static Player wastelander;	
	private Sound backgroundMusic;
	
    public void initialize() 
    {   
    	//MAP HAS TO BE LOADED IN FIRST BEFORE ANY OTHER ACTOR
    	map = new Map("wasteland_test_map.tmx", mainStage);

    	//Otherwise the actor wont be drawn and its events wont be called	
    	merchant = new Npc(565, 420, mainStage, uiTable);
    	wastelander = new Player(530, 400, mainStage, uiTable);
    	merchant.addListener(new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			if(!merchant.getShop().isVisible() && wastelander.isWithinDistance(20, merchant))
    				merchant.getShop().setVisible(true);
    			else {
    				merchant.getShop().setVisible(false);
    			}
    		}
    	});

    	uiTable.setVisible(true);	
    	
    	backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("backgroundmusic.mp3"));
    	backgroundMusic.loop(0.1f);
    }
    
    public void update(float dt)
    {
    	if(!wastelander.isWithinDistance(20, merchant) && merchant.getShop().isVisible()) {
    		merchant.getShop().setVisible(false);
    	}
    	Vector2 wastelanderCoordinates = new Vector2((int)wastelander.getX(), (int)wastelander.getY());
    	WastelandTrash trash = map.getTrashAt(wastelanderCoordinates, wastelander);
    	if(trash != null)
    	{
    		if(wastelander.overlaps(trash)) {
    			trash.remove();
    			//wastelander.getInventory().addItem(item);
    			System.out.println(trash.getName());
    		}
    		trash.destroy();
//    		map.printObjectState();
    	}
    	mainStage.act(dt);
    	uiStage.act(dt);
    }
    
}