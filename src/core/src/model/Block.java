package model;

import com.badlogic.gdx.math.Vector2;

public class Block extends GameElement {
	public Vector2 defaultPosition = new Vector2 (150,100); // Default position of a block
	
	// Constructors
	public Block() {
		super();
		this.setPosition(defaultPosition);
	}
	public Block(Vector2 position) {
		super();
		this.setPosition(position);
	}
	
	// Getters & Setters
	@Override
	public float getHeight() {
		return 16;
	}
	@Override
	public void setPosition(Vector2 pos) {
		this.position = pos;
	}
	@Override
	public Vector2 getPosition() {
		return position;
	}
	@Override
	public float getWidth() {
		return 16;
	}
}
