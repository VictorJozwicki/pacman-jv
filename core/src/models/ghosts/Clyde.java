package models.ghosts;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

import models.GameElement;
import models.Ghost;
import models.World;
import view.TextureFactory;

public class Clyde extends Ghost {
	private Vector2 clydeSpeedVector = new Vector2(-ghostMovementSpeed, 0);
	private int randomNum = -1;
	private int i = 0;
	
	public Clyde() {
		super(new Vector2 (208,256));
		System.out.println("Clyde has entered the game : \"Aghaggahqqhzrqjz\"");
	}
	
	protected boolean moveOut() {
		i++;
		if( i*ghostMovementSpeed > 48 )
			return true;
		
		this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y+ghostMovementSpeed));
		
		return false;
	}
	
	public boolean move(Vector2 pacmanPos, World world, float delta) {
		ArrayList<GameElement> l = new ArrayList<GameElement>();
		Random rand = new Random();
		
		// Teleporation (tunnels)
			if( this.getPosition().x == 0.0 && this.getPosition().y == 256 && clydeSpeedVector.x < 0 )
				this.setPosition(new Vector2(432,256));
			if( this.getPosition().x == 432 && this.getPosition().y == 256 && clydeSpeedVector.x > 0 )
				this.setPosition(new Vector2(0,256));

		if( this.moveOut() ) { // Allow the ghost to go out of his box
			if( this.getPosition().x%16 == 0 && this.getPosition().y%16 == 0 ) {
				l = this.checkSurrounding(world);
				
				if( l.size() > 2 || (l.size() == 2 && !(l.get(0).getPosition().x == l.get(1).getPosition().x || l.get(0).getPosition().y == l.get(1).getPosition().y )) ) { // Si ya deux ou plus

					// Gives a randomNum depending on how much Pellet/SuperPellet there is
					randomNum = rand.nextInt((l.size() - 1) + 1);

					Vector2 positionGameElement = new Vector2(this.getPosition()).sub(l.get(randomNum).getPosition());
					
					if( positionGameElement.y < 0)
						clydeSpeedVector = new Vector2(0, ghostMovementSpeed);
					else if ( positionGameElement.x < 0)
						clydeSpeedVector = new Vector2(ghostMovementSpeed, 0);
					else if ( positionGameElement.y > 0)
						clydeSpeedVector = new Vector2(0, -ghostMovementSpeed);
					else if ( positionGameElement.x > 0)
						clydeSpeedVector = new Vector2(-ghostMovementSpeed, 0);
				}				
			}
			this.getPosition().add(clydeSpeedVector);
			// If ghost touches pacman
			if( (this.getPosition().x <= pacmanPos.x && this.getPosition().x+17 > pacmanPos.x) && (this.getPosition().y <= pacmanPos.y && this.getPosition().y+17 > pacmanPos.y))
				if( !world.superMode )
					return true;
				else {
					//TextureFactory.getInstance().setOtherTexture(this, null, "ghostDead", null);

					//dead = true;

					//path = world.getMaze().findPath(new Vector2((((int)position.x/16)*16), ((int)position.y/16)*16), new Vector2 (16,16));
					//System.out.println("path size : "+path.size());
					this.setPosition(new Vector2 (208,256));
					TextureFactory.getInstance().setOtherTexture(this, null, "clydeNormal", null);
					i = 0;
					clydeSpeedVector = new Vector2();
					randomNum = -1;
			}
		}
		return false;
	}
	
	private ArrayList<GameElement> checkSurrounding(World world ) {
		ArrayList<GameElement> l = new ArrayList<GameElement>();
		
		addTo(world.getMaze().getElementAt(this.getPosition().x, this.getPosition().y+16), l);
		addTo(world.getMaze().getElementAt(this.getPosition().x+16, this.getPosition().y), l);
		addTo(world.getMaze().getElementAt(this.getPosition().x, this.getPosition().y-16), l);
		addTo(world.getMaze().getElementAt(this.getPosition().x-16, this.getPosition().y), l);
		return l;
	}
	private void addTo(GameElement ge, ArrayList<GameElement> l) {
		if( ge != null && !ge.getClass().getName().equals("models.Block") )
			l.add(ge);
	}
}
