package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import view.TextureFactory;

public class MenuScreen implements Screen {
	private SpriteBatch batch = new SpriteBatch();
	private BitmapFont bitmap = new BitmapFont();
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
		batch.begin();
			// Pacman
			batch.draw(TextureFactory.getInstance().getOtherTexture("pacman"), 200, 320);
			// New game
			bitmap.setColor(255, 255, 0, 1);
			bitmap.draw(batch, "Press enter for New Game", 165, 250);
			// Movements
			bitmap.getData().setScale((float) 1.1);
			bitmap.draw(batch, "Movements : Z, Q, S, D", 175, 150);
		batch.end();
		
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
