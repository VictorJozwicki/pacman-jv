package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameElement {
	public Vector2 position;
	public Texture texture;
	
	public GameElement() {
		super();
		this.setPosition(new Vector2());
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Vector2 getPosition() {
		return null;
	}
	public float getWidth() {
		return 1;
	}
	public float getHeight() {
		return 1;
	}
}
