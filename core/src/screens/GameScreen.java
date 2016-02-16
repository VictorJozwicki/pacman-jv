package screens;

import com.badlogic.gdx.Screen;

import models.World;
import view.WorldRenderer;

public class GameScreen implements Screen {
	private World world;
	private WorldRenderer worldRenderer;
	
	public GameScreen() {
		super();
		this.dispose();
		world = new World();
		worldRenderer = new WorldRenderer(world);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
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
	}
}
