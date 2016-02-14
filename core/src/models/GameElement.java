package models;

import com.badlogic.gdx.math.Vector2;

public class GameElement {
	protected Vector2 position;
	protected float width = 16, height = 16;

	public GameElement() {
		super();
		this.setPosition(new Vector2());
	}

	// Getters & Setters
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Vector2 getPosition() {
		return this.position;
	}
	public float getWidth() {
		return this.width;
	}
	public float getHeight() {
		return this.height;
	}
}