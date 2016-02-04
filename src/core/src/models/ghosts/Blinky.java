package models.ghosts;

import com.badlogic.gdx.math.Vector2;

import models.Ghost;

public class Blinky extends Ghost {

	public Blinky() {
		super(new Vector2 (192,256));
		System.out.println("Blinky has entered the game : \"Coming for you Pacman\"");
	}
}
