package models;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import models.ghosts.Blinky;
import models.ghosts.Clyde;
import models.ghosts.Inky;
import models.ghosts.Pinky;

public class World implements Iterable<GameElement> {
	private Pacman pacman;
	private Maze maze;
	private Ghost blinky, pinky, inky, clyde;
	SpriteBatch batch = new SpriteBatch();
	public Music pacmanDeath, pacmanWakaWaka, pacmanBeginningMusic, ghostSiren; // Probably in a music manager, maybe with a HashMap<String, Music>
	
	public World() {
		super();
		maze = new Maze();
		beginningMusic();
		pacman = new Pacman(new Vector2 (224,112));
		blinky = new Blinky();
		pinky = new Pinky();
		inky = new Inky();
		clyde = new Clyde();
		System.out.println("Get ready");
	}
	public int getHeight() {
		return 496;
	}
	public int getWidth() {
		return 448;
	}
	public Pacman getPacman() {
		return this.pacman;
	}
	public Maze getMaze() {
		return this.maze;
	}
	public Ghost getBlinky() {
		return this.blinky;
	}
	public Ghost getPinky() {
		return this.pinky;
	}
	public Ghost getInky() {
		return this.inky;
	}
	public Ghost getClyde() {
		return this.clyde;
	}
	
	// Constructors
	@Override
	public Iterator<GameElement> iterator() {
		ArrayList<GameElement> l = new ArrayList<GameElement>();
		for (GameElement ge : maze) { // Put every pieces of the Maze inside World to render
			l.add(ge);
		}
		l.add(pacman);
		l.add(blinky);
		l.add(pinky);
		l.add(inky);
		l.add(clyde);
		return l.iterator();
	}
	
	// Musics or sound (for fun, might not be there but in a sound manager)
	public void beginningMusic() {
		pacmanBeginningMusic = Gdx.audio.newMusic(Gdx.files.internal("music/pacman_beginning.mp3"));
		pacmanBeginningMusic.play();
	}
	public void pacmanDeath() {
		pacmanBeginningMusic = Gdx.audio.newMusic(Gdx.files.internal("music/pacman_death.mp3"));
		pacmanBeginningMusic.play();
	}
	public void pacmanWakaWaka(boolean bool) {
		if( bool ) {
			pacmanWakaWaka = Gdx.audio.newMusic(Gdx.files.internal("music/pacman_waka_waka.wav"));
			ghostSiren = Gdx.audio.newMusic(Gdx.files.internal("music/ghost_siren.wav"));
			pacmanWakaWaka.setLooping(bool);
			pacmanWakaWaka.play();
			ghostSiren.setLooping(bool);
			ghostSiren.play();
		} else {
			pacmanWakaWaka.stop();
			ghostSiren.stop();
			pacmanWakaWaka.dispose();
			ghostSiren.dispose();
		}
	}
}
