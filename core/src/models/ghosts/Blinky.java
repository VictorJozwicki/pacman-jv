package models.ghosts;

import com.badlogic.gdx.math.Vector2;

import models.Ghost;

public class Blinky extends Ghost {
	private float blinkyMovementSpeed = 0.5f;

	public Blinky() {
		super(new Vector2 (192,256));
		System.out.println("Blinky has entered the game : \"Coming for you Pacman\"");
	}
	
	public boolean move(Vector2 pacmanPos) {
		if( this.getPosition().x < pacmanPos.x)
			this.setPosition(new Vector2(this.getPosition().x+blinkyMovementSpeed, this.getPosition().y));
		if( this.getPosition().x > pacmanPos.x)
			this.setPosition(new Vector2(this.getPosition().x-blinkyMovementSpeed, this.getPosition().y));
		if( this.getPosition().y < pacmanPos.y)
			this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y+blinkyMovementSpeed));
		if( this.getPosition().y > pacmanPos.y)
			this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y-blinkyMovementSpeed));
		if( (this.getPosition().x == pacmanPos.x) && (this.getPosition().y == pacmanPos.y)) {
			return true;
		}
		return false;
	}
}
