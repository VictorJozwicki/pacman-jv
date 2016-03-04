package models.ghosts;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import models.Ghost;
import models.World;
import utility.Node;
import view.TextureFactory;

public class Blinky extends Ghost {
	private int i = 0;
	Vector2 blinkySpeedVector = new Vector2(0, 0);
	private boolean dead = false;
	ArrayList<Node> path;

	public Blinky() {
		super(new Vector2 (192,256));
		System.out.println("Blinky has entered the game : \"Coming for you Pacman\"");
	}
	
	protected boolean moveOut() {
		i++;
		int a = (int) (i*ghostMovementSpeed);
		if( a >= 16 && a < 64 )
			this.setPosition(new Vector2(this.getPosition().x, this.getPosition().y+ghostMovementSpeed));
		else if( a <= 64 )
			this.setPosition(new Vector2(this.getPosition().x+ghostMovementSpeed, this.getPosition().y));
		else
			return true;
		return false;
	}
	
/*	public boolean move(Vector2 pacmanPos, World world, float delta) {
		if( this.moveOut() ) {
			if( timer > 1f ) {
				path = world.getMaze().findPath(new Vector2((((int)position.x/16)*16), ((int)position.y/16)*16), new Vector2((((int)pacmanPos.x/16)*16), ((int)pacmanPos.y/16)*16));
				timer = 0;
			}
			timer += delta;

			if(timerMove > .5f && path!= null && !path.isEmpty()) {
				Node node = path.get(path.size()-1);
					
				position.x = node.tile.x;
				position.y = node.tile.y;

				path.remove(node);
				timerMove = 0;
			}
			timerMove += delta;
		}

		return false;
	}*/
	
	public boolean move(Vector2 pacmanPos, World world, float delta) {
		 if(!dead) {
			if( this.moveOut() ) {
				if( this.getPosition().x%16 == 0 && this.getPosition().y%16 == 0 )	
					path = world.getMaze().findPath(new Vector2((((int)position.x/16)*16), ((int)position.y/16)*16), new Vector2((((int)pacmanPos.x/16)*16), ((int)pacmanPos.y/16)*16));

				if(path!= null && !path.isEmpty()) {
					Node node = path.get(path.size()-1);

					if( this.getPosition().x%16 == 0 && this.getPosition().y%16 == 0 ) {
						Vector2 positionNode = new Vector2(this.getPosition()).sub(node.tile);

						if( positionNode.y < 0 )
							blinkySpeedVector = new Vector2(0, ghostMovementSpeed);
						else if ( positionNode.x < 0)
							blinkySpeedVector = new Vector2(ghostMovementSpeed, 0);
						else if ( positionNode.y > 0)
							blinkySpeedVector = new Vector2(0, -ghostMovementSpeed);
						else if ( positionNode.x > 0)
							blinkySpeedVector = new Vector2(-ghostMovementSpeed, 0);
					}
				}
			}
			this.getPosition().add(blinkySpeedVector);
		}/* else {
			if(path!= null && !path.isEmpty()) {
				Node node = path.get(path.size()-1);

				Vector2 positionNode = new Vector2(this.getPosition()).sub(node.tile);

				if( positionNode.y < 0 )
					blinkySpeedVector = new Vector2(0, ghostMovementSpeed);
				else if ( positionNode.x < 0)
					blinkySpeedVector = new Vector2(ghostMovementSpeed, 0);
				else if ( positionNode.y > 0)
					blinkySpeedVector = new Vector2(0, -ghostMovementSpeed);
				else if ( positionNode.x > 0)
					blinkySpeedVector = new Vector2(-ghostMovementSpeed, 0);

				System.out.println("dead");

				path.remove(path.size()-1);
			}
			this.getPosition().add(blinkySpeedVector);
		}*/
		if( (this.getPosition().x <= pacmanPos.x && this.getPosition().x+17 > pacmanPos.x) && (this.getPosition().y <= pacmanPos.y && this.getPosition().y+17 > pacmanPos.y))
			if( !world.superMode )
				return true;
			else {
				//TextureFactory.getInstance().setOtherTexture(this, null, "ghostDead", null);

				//dead = true;

				//path = world.getMaze().findPath(new Vector2((((int)position.x/16)*16), ((int)position.y/16)*16), new Vector2 (16,16));
				//System.out.println("path size : "+path.size());
				this.setPosition(new Vector2 (192,256));
				TextureFactory.getInstance().setOtherTexture(this, null, "blinkyNormal", null);
				i = 0;
				path = null;
				blinkySpeedVector = new Vector2();
		}
		

		return false;
	}
}
