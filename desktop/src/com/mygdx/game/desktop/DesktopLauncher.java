package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.PacmanJV;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pacman";
		cfg.width = 548;
		cfg.height = 496;
		cfg.resizable = false;
		
		new LwjglApplication(new PacmanJV(), cfg);
	}
}