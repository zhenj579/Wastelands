package com.mygdx.game;


import com.badlogic.gdx.math.Vector2;


public class LevelScreen extends BaseScreen
{
    Map map;
	private Player wastelander;
	
	
    public void initialize() 
    {   
    	map = new Map("wasteland_test_map.tmx", mainStage);
    	wastelander = new Player(590, 400, mainStage, uiTable);
//    	Texture buttonTex = new Texture( Gdx.files.internal("rmcloseup.png") );
//    	TextureRegion buttonRegion = new TextureRegion(buttonTex);
//    	ButtonStyle buttonStyle = new ButtonStyle();
//    	buttonStyle.up = new TextureRegionDrawable( buttonRegion );
//    	Button test = new Button(buttonStyle);
//    	uiTable.add(test);
//    	uiTable.row();
//    	Label playerName = new Label("John", BaseGame.labelStyle);
//    	uiTable.add(playerName);
//    	uiTable.row();
//    	uiTable.add().padBottom(200);
    	uiTable.setVisible(true);	
    	
    }
    
    public void update(float dt)
    {
    	Vector2 wastelanderCoordinates = new Vector2((int)wastelander.getX(), (int)wastelander.getY());
    	WastelandTrash trash = map.getTrashAt(wastelanderCoordinates, wastelander);
    	if(trash != null)
    	{
    		trash.destroy();
//    		map.printObjectState();
    	}
    	mainStage.act(dt);
    }
    
}