package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
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
		cfg.addIcon("icons/pacman128.png", Files.FileType.Internal);
		cfg.addIcon("icons/pacman32.png", Files.FileType.Internal);
		cfg.addIcon("icons/pacman16.png", Files.FileType.Internal);
		
		new LwjglApplication(new PacmanJV(), cfg);
	}
}
