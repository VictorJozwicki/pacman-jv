package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import models.GameElement;
import models.Pellet;
import models.SuperPellet;
import models.World;

public class WorldRenderer {
	// ------------------------------ VARIABLES ------------------------------
	// General
	private World world;
	private SpriteBatch batch = new SpriteBatch();
	private BitmapFont muted = new BitmapFont(), scoreBitmap = new BitmapFont();
	private float pacmanMovementSpeed = 1f, blinkyMovementSpeed = 0f;
	private boolean isMuted = false, dead = false;
	private Vector2 speedVector = new Vector2(0,0);
	private float scoreMultiplicator = 1, scorePellet = 12, scoreSuperPellet = 500, score = scorePellet * scoreMultiplicator;
	// Collisions detection
	private GameElement elementAt, elementAtCurrent;
	// Direction manager
	private int nextDirection = 0, currentDirection = 0;
	private int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;
	// Other
	private boolean canMove = false, isFirstRender = true, showMessageMuted = false;
	private float timeBeforeMoving = 0.3f, timeSinceFirstRender = 0;
	// Music -- Probably in a music manager, maybe with a HashMap<String, Music>
	private Music pacmanWakaWaka, ghostSiren;
	private Sound pacmanDeath, pacmanBeginningMusic;
	// ------------------------------ END OF VARIABLES ------------------------------
	
	// Constructor
	public WorldRenderer(World world) {
		super();
		this.world = world;
	}

	// Functions
	public void render(float delta) {
		// Render the world
		batch.begin();
			for (GameElement ge : world) {	// Render every element of world
				if( !(ge.getClass().getName() == "models.GameElement") )
					batch.draw(TextureFactory.getInstance().getTexture(ge), ge.getPosition().x, ge.getPosition().y, ge.getWidth(), ge.getHeight());
			}
		batch.end();
		
		// Does not allow Pacman and the Ghosts to move before the beginning music stops
		this.beginningTimer(delta);
		// Quit the game (exit application)
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
		// Mute the sounds
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			if( isMuted ) {
				this.playAllMusics();
				isMuted = false;
				showMessageMuted = false;
				System.out.println("Unmuted");
			} else {
				this.mute();
				isMuted = true;
				showMessageMuted = true;
				System.out.println("Muted");

			}
		}
		// If not dead
		if( !dead && canMove) {
			// Collisions
			this.checkCollisions();
			// Move blinky
			this.moveBlinky();
			//this.moveInky();
			// Directions
			if (Gdx.input.isKeyPressed(Keys.Z)) {
				nextDirection = UP;
			}
			if (Gdx.input.isKeyPressed(Keys.D)) {
				nextDirection = RIGHT;
			}
			if (Gdx.input.isKeyPressed(Keys.S)) {
				nextDirection = DOWN;
			}
			if (Gdx.input.isKeyPressed(Keys.Q)) {
				nextDirection = LEFT;
			}
		} // End directions
		
		// Show muted
		this.showMuted();
		// Show score
		this.showScore();
		
	}
	
	private void checkCollisions() {
		// Positions of Pacman when checking collisions
		float pacmanX = world.getPacman().getPosition().x, pacmanY = world.getPacman().getPosition().y;
		
		// Teleporation (tunnels)
		if( pacmanX == 0.0 && pacmanY == 256 && currentDirection == 4)
			world.getPacman().setPosition(new Vector2(432,256));
		if( pacmanX == 432 && pacmanY == 256 && currentDirection == 2)
			world.getPacman().setPosition(new Vector2(0,256));

		// It's when I check for collisions
		if( pacmanX%16 == 0 && pacmanY%16 == 0) {
			// Checking if there is an element in all directions for NEXT direction
			if( this.nextDirection == 1 ) { // UP
				elementAt = world.getMaze().getElementAt(pacmanX, pacmanY+16);
			} else if ( this.nextDirection == 2 ) { // RIGHT
				elementAt = world.getMaze().getElementAt(pacmanX+16, pacmanY);
			} else if ( this.nextDirection == 3 ) { // DOWN
				elementAt = world.getMaze().getElementAt(pacmanX, pacmanY-16);
			} else if ( this.nextDirection == 4 ) { // LEFT
				elementAt = world.getMaze().getElementAt(pacmanX-16, pacmanY);
			}
			// Checking if there is an element in all directions for CURRENT direction
			if( this.currentDirection == 1 ) { // UP
				elementAtCurrent = world.getMaze().getElementAt(pacmanX, pacmanY+16);
			} else if ( this.currentDirection == 2 ) { // RIGHT
				elementAtCurrent = world.getMaze().getElementAt(pacmanX+16, pacmanY);
			} else if ( this.currentDirection == 3 ) { // DOWN
				elementAtCurrent = world.getMaze().getElementAt(pacmanX, pacmanY-16);
			} else if ( this.currentDirection == 4 ) { // LEFT
				elementAtCurrent = world.getMaze().getElementAt(pacmanX-16, pacmanY);
			}
			
			// If the element isn't null, we can move so current direction = next direction
			if( elementAt != null || elementAtCurrent != null ) {
				this.checkForPellets();
				if( !elementAt.getClass().getName().equals("models.Block") ) {
					// Update current direction with next
					currentDirection = nextDirection;
					if( this.currentDirection == 1 ) { // UP
						speedVector = new Vector2(0, pacmanMovementSpeed);
					} else if ( this.currentDirection == 2 ) { // RIGHT
						speedVector = new Vector2(pacmanMovementSpeed, 0);
					} else if ( this.currentDirection == 3 ) { // DOWN
						speedVector = new Vector2(0, -pacmanMovementSpeed);
					} else if ( this.currentDirection == 4 ) { // LEFT
						speedVector = new Vector2(-pacmanMovementSpeed, 0);
					}
					this.world.getPacman().getPosition().add(speedVector);
				} else if ( elementAtCurrent.getClass().getName().equals("models.Block") ) {
					speedVector = new Vector2(0, 0);
				} else {
					if( currentDirection != nextDirection ) {
						this.world.getPacman().getPosition().add(speedVector);
					} else {
						speedVector = new Vector2(0, 0);
					}
				}
			}
		} else {
			this.world.getPacman().getPosition().add(speedVector);
		}
	}
	
	private void checkForPellets() {
		if( elementAtCurrent != null && elementAtCurrent.getClass().equals(Pellet.class) ) {
			world.getMaze().deleteElementAt(elementAtCurrent);
			score = score + (scoreMultiplicator * scorePellet);
		} 
		if ( nextDirection != currentDirection && elementAt != null && elementAt.getClass().equals(Pellet.class) ) {
			world.getMaze().deleteElementAt(elementAt);
			score = score + (scoreMultiplicator * scorePellet);
		}
		if ( nextDirection != currentDirection && elementAt != null && elementAt.getClass().equals(SuperPellet.class) ) {
			world.getMaze().deleteElementAt(elementAt);
			score = score + (scoreMultiplicator * scoreSuperPellet);
		}
		if ( elementAt != null && elementAt.getClass().equals(SuperPellet.class) ) {
			world.getMaze().deleteElementAt(elementAt);
			score = score + (scoreMultiplicator * scoreSuperPellet);
		}
	}
	
	private void beginningTimer(float delta) {
		if( !canMove ) { // If PacMan cannot move (first time)
			if( isFirstRender ) { // If it's the first render --> Play music
				beginningMusic(true);
				isFirstRender = false;
			}
			nextDirection = 0; // Not allowed to move
			timeSinceFirstRender += delta; // Calculate the time since first render to allow him to move after X seconds
			if( timeSinceFirstRender >= timeBeforeMoving ) {
				canMove = true; // He can move
				// Play sounds & musics
				this.playAllMusics();
			}
		}
	}
	
	private void moveBlinky() { // Probably useless
		Vector2 pacmanPos = world.getPacman().getPosition();
		if( !dead ) {
			if( world.getBlinky().getPosition().x < pacmanPos.x)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x+blinkyMovementSpeed, world.getBlinky().getPosition().y));
			if( world.getBlinky().getPosition().x > pacmanPos.x)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x-blinkyMovementSpeed, world.getBlinky().getPosition().y));
			if( world.getBlinky().getPosition().y < pacmanPos.y)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x, world.getBlinky().getPosition().y+blinkyMovementSpeed));
			if( world.getBlinky().getPosition().y > pacmanPos.y)
				world.getBlinky().setPosition(new Vector2(world.getBlinky().getPosition().x, world.getBlinky().getPosition().y-blinkyMovementSpeed));
			if( (world.getBlinky().getPosition().x == pacmanPos.x) && (world.getBlinky().getPosition().y == pacmanPos.y)) {
				dead = true;
				this.pacmanDeath(true);
				this.pacmanWakaWaka(false);
				this.ghostSiren(false);
			}
		}
	}
	
	private void showMuted() {
		if( showMessageMuted ) {
			batch.begin();
				muted.setColor(1, 0, 0, 1);
				muted.draw(batch, "Muted", 478, 488);
			batch.end();
		} else {
			showMessageMuted = false;
		}
	}
	
	private void showScore() {
		batch.begin();
		scoreBitmap.setColor(1, 0, 0, 1);
		scoreBitmap.draw(batch, String.valueOf(score), 488, 420);
	batch.end();
	}
	
	// ---------- Musics & Sounds (might not be there but in a sound manager) ----------
	// Sounds : Played once
	private void beginningMusic(boolean bool) {
		if( bool ) {
			pacmanBeginningMusic = Gdx.audio.newSound(Gdx.files.internal("music/pacman_beginning.mp3"));
			pacmanBeginningMusic.play();
		} else
			pacmanBeginningMusic.stop();
	}
	private void pacmanDeath(boolean bool) {
		if( bool ) {
			pacmanDeath = Gdx.audio.newSound(Gdx.files.internal("music/pacman_death.mp3"));
			pacmanDeath.play();
		} else
			pacmanDeath.stop();
	}
	// Music --> repeated or played
	private void playAllMusics() {
		this.pacmanWakaWaka(true);
		this.ghostSiren(true);
	}
	private void pacmanWakaWaka(boolean bool) {
		if( bool ) {
			pacmanWakaWaka = Gdx.audio.newMusic(Gdx.files.internal("music/pacman_waka_waka.wav"));
			pacmanWakaWaka.setLooping(bool);
			pacmanWakaWaka.play();
		} else if( pacmanWakaWaka != null ){
			pacmanWakaWaka.stop();
			pacmanWakaWaka.dispose();
		}
	}
	private void ghostSiren(boolean bool) {
		if( bool ) {
			ghostSiren = Gdx.audio.newMusic(Gdx.files.internal("music/ghost_siren.wav"));
			ghostSiren.setLooping(bool);
			ghostSiren.play();
		} else if( ghostSiren != null ) {
			
			ghostSiren.stop();
			ghostSiren.dispose();
		}
	}
	// Common to sounds & music
	private void mute() {
		this.pacmanWakaWaka(false);
		this.ghostSiren(false);
		this.beginningMusic(false);
	}
	// ---------- End of Musics & Sounds ----------
}