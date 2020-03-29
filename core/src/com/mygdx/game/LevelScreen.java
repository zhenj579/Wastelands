package com.mygdx.game;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class LevelScreen extends BaseScreen
{
    private Map map;
    private Npc merchant;
	private Player wastelander;	
	
    public void initialize() 
    {   
    	//MAP HAS TO BE LOADED IN FIRST BEFORE ANY OTHER ACTOR
    	map = new Map("wasteland_test_map.tmx", mainStage);
    	//Otherwise the actor wont be drawn and its events wont be called	
    	merchant = new Npc(700, 400, mainStage, uiTable);
    	wastelander = new Player(600, 400, mainStage, uiTable);
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
    }

    public void update(float dt)
    {
    	Vector2 wastelanderCoordinates = new Vector2((int)wastelander.getX(), (int)wastelander.getY());
    	WastelandTrash trash = map.getTrashAt(wastelanderCoordinates);
    	if(trash != null)
    	{
    		if(wastelander.overlaps(trash)) {
    			trash.remove();
    			//wastelander.getInventory().addItem(item);
    			System.out.println(trash.getName());
    		}
    	}
    	mainStage.act(dt);
    	uiStage.act(dt);
    }
    
}