package view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import model.GameElement;
import model.Maze;
import model.World;

public class WorldRenderer {
	World world;
	Maze maze;
	SpriteBatch batch = new SpriteBatch();
	
	public WorldRenderer(World world) {
		super();
		this.world = world;
	}
	
	public void render(float delta) {
		batch.begin();
			for (GameElement ge : world) { // Render every element of world
				batch.draw(TextureFactory.getInstance().getTexture(ge), ge.getPosition().x, ge.getPosition().y, ge.getWidth(), ge.getHeight());
			}
		batch.end();
		
		//Outdated
		/*if (Gdx.input.isKeyPressed(Keys.UP))
			pacman.goUp();
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			pacman.goDown();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			pacman.goRight();
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			pacman.goLeft();*/
		// EXIT
		
		batch.begin();
	    //batch.draw(pacman..txture, pacman.getPosition().x, pacman.getPosition().y, pacman.getWidth(), pacman.getHeight());
		batch.end();
	}
}
