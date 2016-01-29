package model;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World implements Iterable<GameElement> {
	private Pacman pacman;
	SpriteBatch batch = new SpriteBatch();
	Maze maze = new Maze();
	Block b1;
	
	public World() {
		super();
		pacman = this.getPacman();
	}
	public int getHeight() {
		return 600;
	}
	public int getWidth() {
		return 600;
	}
	public Pacman getPacman() {
		return new Pacman();
	}
	public Maze getMaze() {
		return new Maze();
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		ArrayList<GameElement> l = new ArrayList<GameElement>();
		l.add(pacman);
		for (GameElement ge : maze) { // Put every pieces of the Maze inside World to render
			l.add(ge);
		}		
		return l.iterator();
	}
}
