package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Wastelanders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Wastelands";
		//config.fullscreen = true;
		config.height = 1080;
		config.width = 1920;
		new LwjglApplication(new Wastelanders(), config);
		
	}
}
