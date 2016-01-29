package model;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends GameElement {
	public Vector2 defaultPosition = new Vector2 (176,160);
	
	// Constructor
	public Pacman() {
		super();
		this.setPosition(defaultPosition);
	}
	public Pacman(Vector2 position) {
		super();
		this.setPosition(position);
	}
	
	// Getters & Setters
	@Override
	public float getWidth() {
		return 16;
	}
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
	// Functions
	
	// Outdated
	/*public void goLeft() {
		texture = TextureFactory.getInstance().getPacmanTextureLeft1();
		if(this.position.x - movement > 0)
			this.position.x = this.position.x - movement;
	}
	public void goRight() {
		texture = TextureFactory.getInstance().getPacmanTextureRight1();
		if(this.position.x + movement < 600 - this.getWidth())
			this.position.x = this.position.x + movement;
	}
	public void goUp() {
		texture = TextureFactory.getInstance().getPacmanTextureUp1();
		if(this.position.y + movement < 600 - this.getHeight())
			this.position.y = this.position.y + movement;
	}
	public void goDown() {
		texture = TextureFactory.getInstance().getPacmanTextureDown1();
		if(this.position.y - movement > 0)
			this.position.y = this.position.y - movement;
	}*/
}
