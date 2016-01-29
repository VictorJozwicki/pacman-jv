package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import model.World;
import view.TextureFactory;
import view.WorldRenderer;

public class GameScreen implements Screen {
	//Texture block = TextureFactory.getInstance().getBlockTexture();
	Texture pacmanLeft1 = TextureFactory.getInstance().getPacmanTextureLeft1();
	Texture pacmanLeft2 = TextureFactory.getInstance().getPacmanTextureLeft2();
	World world = new World();
	WorldRenderer worldRenderer;
	
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
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldRenderer.render(delta);
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
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
		// TODO Auto-generated method stub
		
	}

}
