package models.ghosts;

import com.badlogic.gdx.math.Vector2;

import models.Ghost;

public class Pinky extends Ghost {

	public Pinky() {
		super(new Vector2 (240,256));
		System.out.println("Pinky has entered the game : \"Gonna ambush this Pacman\"");
	}
}
