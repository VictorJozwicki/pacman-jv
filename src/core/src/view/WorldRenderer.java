package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import models.GameElement;
import models.World;

public class WorldRenderer {
	private World world;
	SpriteBatch batch = new SpriteBatch();
	float movementSpeed = 2f;
	boolean dead = false, canMove = false;
	float foo = 0;
	int nextDirection = 0, currentDirection = 0;
	int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4; // Direction with int
	Vector2 speedVector;
	
	public WorldRenderer(World world) {
		super();
		this.world = world;
	}
	
	public void render(float delta) {
		batch.begin();
			for (GameElement ge : world) // Render every element of world
				batch.draw(TextureFactory.getInstance().getTexture(ge), ge.getPosition().x, ge.getPosition().y, ge.getWidth(), ge.getHeight());
		batch.end();
		
		// Quit
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
		if( !canMove ) {
			nextDirection = 0;
			foo += delta;
			if( foo >= 1 ) {
				canMove = true;
				world.pacmanWakaWaka(true);
			}
		}
		if( nextDirection != 0 && !dead) {
			if( !checkColision() ) {
				this.world.getPacman().getPosition().add(speedVector);
			}
		}
		// If not dead
		if( !dead && canMove) {
			// Move blinky
			this.moveBlinky();
			// Directions getPosition().add(vecteur de vitesse) et à chaque render --> rappeler le move();
			if (Gdx.input.isKeyPressed(Keys.D)) {
				speedVector = new Vector2(movementSpeed, 0);
				nextDirection = RIGHT;
			}
			if (Gdx.input.isKeyPressed(Keys.Q)) {
				speedVector = new Vector2(-movementSpeed, 0);
				nextDirection = LEFT;
			}
			if (Gdx.input.isKeyPressed(Keys.Z)) {
				speedVector = new Vector2(0, movementSpeed);
				nextDirection = UP;
			}
			if (Gdx.input.isKeyPressed(Keys.S)) {
				speedVector = new Vector2(0, -movementSpeed);
				nextDirection = DOWN;
			}
		} // End directions
	}
	
	public boolean checkColision() {
		GameElement temp = null;
		/*temp = world.getMaze().getElementAt(world.getPacman().getPosition().x, world.getPacman().getPosition().y, nextDirection);
		if( temp != null && temp.getClass().getName().equals("models.Block")) {
			System.out.println("Collide");
			return true;
		}*/
		if( world.getMaze().getElementAt(world.getPacman().getPosition().x, world.getPacman().getPosition().y, nextDirection) ) {
			nextDirection = currentDirection;
			System.out.println("Collide");
			return true;
		}
		return false;
	}
	
	public void moveBlinky() { // Probably useless
		Vector2 pacmanPos = world.getPacman().getPosition();
		if( !dead ) {
			if( world.getBlinky().getPosition().x < pacmanPos.x)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x+0.5f, world.getBlinky().getPosition().y));
			if( world.getBlinky().getPosition().x > pacmanPos.x)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x-0.5f, world.getBlinky().getPosition().y));
			if( world.getBlinky().getPosition().y < pacmanPos.y)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x, world.getBlinky().getPosition().y+0.5f));
			if( world.getBlinky().getPosition().y > pacmanPos.y)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x, world.getBlinky().getPosition().y-0.5f));
			if( (world.getBlinky().getPosition().x == pacmanPos.x) && (world.getBlinky().getPosition().y == pacmanPos.y)) {
				dead = true;
				world.pacmanDeath();
				world.pacmanWakaWaka(false);
			}
		}
	}
}