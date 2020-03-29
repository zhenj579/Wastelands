package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LevelScreen extends BaseScreen
{
    Map map;
	private Player wastelander;
	
	
    public void initialize() 
    {   
    	map = new Map("wasteland_test_map.tmx", mainStage);
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
    	wastelander = new Player(600, 400, mainStage, uiTable);
    }

    public void update(float dt)
    {
    	mainStage.act(dt);
    }
    
}