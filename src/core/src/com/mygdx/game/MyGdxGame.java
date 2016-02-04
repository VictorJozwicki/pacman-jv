package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;

import screens.GameScreen;

public class MyGdxGame extends Game {
	FPSLogger fps;
	
	@Override
	public void create () {
		// Create a new screen
		newGame();
		fps = new FPSLogger();
	}

	@Override
	public void render () {
		super.render();
		fps.log();
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			newGame();
		}	
	}
	
	public void newGame() {
		setScreen(new GameScreen());
	}
}