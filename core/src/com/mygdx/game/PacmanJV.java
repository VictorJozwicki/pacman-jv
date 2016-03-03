package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

import screens.GameScreen;
import screens.MenuScreen;

public class PacmanJV extends Game {
	FPSLogger fps;
	
	@Override
	public void create () {
		// Create a new screen
		setScreen(new MenuScreen());
		fps = new FPSLogger();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		fps.log();
		if (Gdx.input.isKeyJustPressed(Keys.ENTER))
			newGame();	
	}
	public void newGame() {
		setScreen(new GameScreen(this));
	}
}