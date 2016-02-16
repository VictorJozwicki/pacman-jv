package models;

import com.badlogic.gdx.math.Vector2;

public class Ghost extends GameElement {
	
	// Constructors
	public Ghost(Vector2 position) {
		super();
		this.setPosition(position);
	}

	public boolean move(Vector2 position) {
		return false;
	}
}
