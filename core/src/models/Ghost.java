package models;

import com.badlogic.gdx.math.Vector2;

public class Ghost extends GameElement {
	protected float ghostMovementSpeed = 1f;
	protected int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;
	// Constructors
	public Ghost(Vector2 position) {
		super();
		this.setPosition(position);
	}

	public boolean move(Vector2 position, World world) {
		return false;
	}
	
	protected boolean moveOut() {
		return false;
	}
}
