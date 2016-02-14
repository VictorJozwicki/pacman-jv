package models;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends GameElement {

	// Constructors
	public Pacman(Vector2 position) {
		super();
		this.setPosition(position);
		System.out.println("Pacman has entered the game - \"Waka waka waka\"");
	}
}
