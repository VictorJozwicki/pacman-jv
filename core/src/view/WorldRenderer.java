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
	private float pacmanMovementSpeed = 1f;
	private boolean isMuted = false, dead = false, win = false;
	private Vector2 speedVector = new Vector2(0,0);
	private float scoreMultiplicator = 1, scorePellet = 12, scoreSuperPellet = 500, score = scorePellet * scoreMultiplicator;
	private int remainingPellets;
	// Collisions detection
	private GameElement elementAt, elementAtNext, elementAtCurrent;
	// Animations
	private float animationTime = 0f, animationSpeed = 6f;
	// Direction manager
	private int nextDirection = 0, currentDirection = 0;
	private int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;
	// Other
	private boolean canMove = true, isFirstRender = true, showMessageMuted = false, ghostSirenIsPlaying = false;
	private float timeBeforeMoving = 4.5f/* 4.5 is the length of the beginning music */, timeSinceFirstRender = 0, timeSinceLastRender = 0;
	private String extension = ".png";
	// Music -- Probably in a music manager, maybe with a HashMap<String, Music>
	private Music ghostSiren;
	private Sound pacmanDeath, pacmanBeginningMusic, pacmanWakaWaka;
	// ------------------------------ END OF VARIABLES ------------------------------
	
	// Constructor
	public WorldRenderer(World world) {
		super();
		this.world = world;
		remainingPellets = this.world.getMaze().numberPellets;
	}

	// Functions
	public void render(float delta) {
		timeSinceLastRender = delta;
		// Render the world
		batch.begin();
			for (GameElement ge : world) {	// Render every element of world
				if( !(ge.getClass().getName() == "models.GameElement") )
					batch.draw(TextureFactory.getInstance().getTexture(ge), ge.getPosition().x, ge.getPosition().y, ge.getWidth(), ge.getHeight());
			}
		batch.end();
		
		// Does not allow Pacman and the Ghosts to move before the beginning music stops
		this.beginningTimer();
		// Quit the game (exit application)
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
		// Mute the sounds
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			if( isMuted ) {
				isMuted = false;
				this.ghostSiren(true);
				showMessageMuted = false;
			} else {
				this.mute();
				isMuted = true;
				showMessageMuted = true;
			}
		}
		// If not dead
		if( !dead && canMove) {
			// Animations
			this.pacmanAnimation();
			// Collisions
			this.checkCollisions();
			// Move blinky
			if( !dead & (world.getBlinky().move(world.getPacman().getPosition(), this.world) || world.getClyde().move(world.getPacman().getPosition(), this.world)) ) {
				dead = true;
				this.pacmanDeath(true);
				this.pacmanWakaWaka(false);
				this.ghostSiren(false);
			}
			if( remainingPellets == 0 ) {
				dead = true;
				this.pacmanWakaWaka(false);
				this.ghostSiren(false);
				win = true;
			}
			//this.moveInky();
			// Directions
			if (Gdx.input.isKeyPressed(Keys.Z))
				nextDirection = UP;
			if (Gdx.input.isKeyPressed(Keys.D))
				nextDirection = RIGHT;
			if (Gdx.input.isKeyPressed(Keys.S))
				nextDirection = DOWN;
			if (Gdx.input.isKeyPressed(Keys.Q))
				nextDirection = LEFT;
		} // End directions
		// Show muted
		this.showMuted();
		// Show score
		this.showScore();
		// Show if won
		this.showWin();
	}
	
	private void checkCollisions() {
		// Positions of Pacman when checking collisions
		float pacmanX = world.getPacman().getPosition().x, pacmanY = world.getPacman().getPosition().y;
		
		// Teleporation (tunnels)
		if( pacmanX == 0.0 && pacmanY == 256 && currentDirection == LEFT)
			world.getPacman().setPosition(new Vector2(432,256));
		if( pacmanX == 432 && pacmanY == 256 && currentDirection == RIGHT)
			world.getPacman().setPosition(new Vector2(0,256));

		// It's when I check for collisions
		if( pacmanX%16 == 0 && pacmanY%16 == 0) {
			this.checkForPellets(pacmanX, pacmanY);
			// Checking if there is an element in all directions for NEXT direction
			if( this.nextDirection == UP ) {
				elementAtNext = world.getMaze().getElementAt(pacmanX, pacmanY+16);
			} else if ( this.nextDirection == RIGHT ) {
				elementAtNext = world.getMaze().getElementAt(pacmanX+16, pacmanY);
			} else if ( this.nextDirection == DOWN ) {
				elementAtNext = world.getMaze().getElementAt(pacmanX, pacmanY-16);
			} else if ( this.nextDirection == LEFT ) {
				elementAtNext = world.getMaze().getElementAt(pacmanX-16, pacmanY);
			}
			// Checking if there is an element in all directions for CURRENT direction
			if( this.currentDirection == UP ) {
				elementAtCurrent = world.getMaze().getElementAt(pacmanX, pacmanY+16);
			} else if ( this.currentDirection == RIGHT ) {
				elementAtCurrent = world.getMaze().getElementAt(pacmanX+16, pacmanY);
			} else if ( this.currentDirection == DOWN ) {
				elementAtCurrent = world.getMaze().getElementAt(pacmanX, pacmanY-16);
			} else if ( this.currentDirection == LEFT ) {
				elementAtCurrent = world.getMaze().getElementAt(pacmanX-16, pacmanY);
			}
			
			// If the element isn't null, we can move so current direction = next direction
			if( elementAtNext != null || elementAtCurrent != null ) {
				if( elementAtNext != null && !elementAtNext.getClass().getName().equals("models.Block") ) {
					// Update current direction with next
					currentDirection = nextDirection;
					if( this.currentDirection == UP ) {
						speedVector = new Vector2(0, pacmanMovementSpeed);
					} else if ( this.currentDirection == RIGHT ) {
						speedVector = new Vector2(pacmanMovementSpeed, 0);
					} else if ( this.currentDirection == DOWN ) {
						speedVector = new Vector2(0, -pacmanMovementSpeed);
					} else if ( this.currentDirection == LEFT ) {
						speedVector = new Vector2(-pacmanMovementSpeed, 0);
					}
					this.world.getPacman().getPosition().add(speedVector);
				} else if ( elementAtCurrent != null && elementAtCurrent.getClass().getName().equals("models.Block") ) {
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
	
	private void pacmanAnimation() {
		String direction = null;
		// Building the direction to know which texture to call
		if( this.currentDirection == UP )
			direction = "Up";
		else if( this.currentDirection == RIGHT )
			direction = "Right";
		else if( this.currentDirection == DOWN )
			direction = "Down";
		else if( this.currentDirection == LEFT )
			direction = "Left";
		// Trying to do so I don't call 10x the same texture per cycles
		if( this.animationTime == animationSpeed ) {
			TextureFactory.getInstance().setOtherTexture(this.world.getPacman(), direction, null, extension);
			this.animationTime++;	
		} else if( this.animationTime <= animationSpeed) {
			this.animationTime++;		
		} else if( this.animationTime == animationSpeed*2 ) {
			TextureFactory.getInstance().setOtherTexture(this.world.getPacman(), direction, "-2", extension);
			this.animationTime++;
		} else if( animationTime <= animationSpeed*2 ) {
			this.animationTime++;
		} else {
			this.animationTime = 0;			
		}
	}
	
	private void checkForPellets(float x, float y) {
		elementAt = world.getMaze().getElementAt(x, y);
		if( elementAt != null ) {
			if ( elementAt.getClass().equals(Pellet.class) ) {
				world.getMaze().deleteElementAt(elementAt);
				this.pacmanWakaWaka(true);
				score = score + (scoreMultiplicator * scorePellet);
				remainingPellets--;
			}
			if ( elementAt.getClass().equals(SuperPellet.class) ) {
				world.getMaze().deleteElementAt(elementAt);
				this.pacmanWakaWaka(true);
				score = score + (scoreMultiplicator * scoreSuperPellet);
				remainingPellets--;
			}
		}
	}
	
	private void beginningTimer() {
		if( !canMove ) { // If PacMan cannot move (first time)
			if( isFirstRender ) { // If it's the first render --> Play music
				beginningMusic(true);
				isFirstRender = false;
			}
			nextDirection = 0; // Not allowed to move
			timeSinceFirstRender += timeSinceLastRender; // Calculate the time since first render to allow him to move after X seconds
			if( timeSinceFirstRender >= timeBeforeMoving ) {
				canMove = true; // He can move
				this.ghostSiren(true);
			}
		}
	}
	// Messages and such
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
	private void showWin() {
		if( win ) {
			batch.begin();
				scoreBitmap.setColor(1, 0, 0, 1);
				scoreBitmap.draw(batch, "YOU WIN", 467, 200);
			batch.end();	
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
		if( bool && !isMuted ) {
			pacmanBeginningMusic = Gdx.audio.newSound(Gdx.files.internal("music/pacman_beginning.mp3"));
			pacmanBeginningMusic.play();
		} else if ( pacmanBeginningMusic != null )
			pacmanBeginningMusic.stop();
	}
	private void pacmanDeath(boolean bool) {
		if( bool && !isMuted ) {
			pacmanDeath = Gdx.audio.newSound(Gdx.files.internal("music/pacman_death.mp3"));
			pacmanDeath.play();
		} else if ( pacmanDeath != null )
			pacmanDeath.stop();
	}
	// Music --> repeated or played
	private void pacmanWakaWaka(boolean bool) {
		if( bool && !isMuted ) {
			pacmanWakaWaka = Gdx.audio.newSound(Gdx.files.internal("music/pacman_waka_waka.wav"));
			pacmanWakaWaka.play();
		} else if( pacmanWakaWaka != null ){
			pacmanWakaWaka.stop();
			pacmanWakaWaka.dispose();
		}
	}
	private void ghostSiren(boolean bool) {
		if( bool && !isMuted && !ghostSirenIsPlaying ) {
			ghostSiren = Gdx.audio.newMusic(Gdx.files.internal("music/ghost_siren.mp3"));
			ghostSiren.setLooping(bool);
			ghostSiren.play();
			ghostSirenIsPlaying = true;
		} else if( ghostSiren != null ) {
			ghostSiren.stop();
			ghostSirenIsPlaying = false;
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