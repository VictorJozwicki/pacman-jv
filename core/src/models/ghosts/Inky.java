package models.ghosts;

import com.badlogic.gdx.math.Vector2;

import models.Ghost;

public class Inky extends Ghost {

	public Inky() {
		super(new Vector2 (224,256));
		System.out.println("Inky has entered the game : \"You\'re gonna be scared\"");
	}
}
