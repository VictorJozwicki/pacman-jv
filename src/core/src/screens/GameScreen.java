package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import models.World;
import view.WorldRenderer;

public class GameScreen implements Screen {
	World world;
	WorldRenderer worldRenderer;
	float foo = 0;
	
	public GameScreen() {
		super();
		world = new World();
		worldRenderer = new WorldRenderer(world);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldRenderer.render(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		world = null;
		worldRenderer = null;
		world.pacmanBeginningMusic.dispose();
	}

}
