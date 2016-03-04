package models;

import java.util.ArrayList;
import java.util.Iterator;

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
	public boolean superMode = false;
	
	public World() {
		super();
		maze = new Maze();
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
}
