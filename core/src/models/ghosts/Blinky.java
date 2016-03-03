package models.ghosts;

import com.badlogic.gdx.math.Vector2;

import models.Ghost;
import models.World;

public class Blinky extends Ghost {
	private boolean canEatPacman = false;

	public Blinky() {
		super(new Vector2 (192,256));
		System.out.println("Blinky has entered the game : \"Coming for you Pacman\"");
	}
	
	protected boolean moveOut() {
		if( this.position.y <= 272 ) {
			this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y+ghostMovementSpeed));		
		} else if( this.position.x <= 208 ) {
			this.setPosition(new Vector2(this.getPosition().x+ghostMovementSpeed, this.getPosition().y));	
		} else if( this.position.y <= 304 )
			this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y+ghostMovementSpeed));
		return true;
	}
	
	public boolean move(Vector2 pacmanPos, World world) {
		//this.moveOut();
		if( this.getPosition().x < pacmanPos.x)
			this.setPosition(new Vector2(this.getPosition().x+ghostMovementSpeed, this.getPosition().y));
		if( this.getPosition().x > pacmanPos.x)
			this.setPosition(new Vector2(this.getPosition().x-ghostMovementSpeed, this.getPosition().y));
		if( this.getPosition().y < pacmanPos.y)
			this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y+ghostMovementSpeed));
		if( this.getPosition().y > pacmanPos.y)
			this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y-ghostMovementSpeed));
		// If ghost touches pacman
		if( (this.getPosition().x == pacmanPos.x) && (this.getPosition().y == pacmanPos.y) && canEatPacman )
			return true;
		return false;
	}
}
