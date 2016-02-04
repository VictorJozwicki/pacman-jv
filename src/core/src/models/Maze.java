package models;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

public class Maze implements Iterable<GameElement> {
	ArrayList<GameElement> l = new ArrayList<GameElement>();
	int height, width;
	int[][] mazeLevel = new int[][] {
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3, 3, 3, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 2, 1 },
		{ 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
		{ 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
	};

	// Constuctors
	public Maze() {
		super();
		System.out.println("Building : maze");
		height = mazeLevel.length;
		width = mazeLevel[0].length;
		for( int y = 0; y <= mazeLevel.length-1; y++) {
			height--;
			for( int i = 0; i <= mazeLevel[height].length-1; i++ )
				this.whichElement(mazeLevel[height][i], i, y);
		}
		System.out.println("Building : maze COMPLETED");
	}
	
	/*public GameElement getElementAt(float x, float y, int direction) {
		if( direction == 1 ) {
			System.out.println("UP");
			for (GameElement ge : l) {
				for( int i = 0; i <= 15; i++) {
					if( ge.getPosition().x == x+i && ge.getPosition().y == y+16 )
						return ge;
					if( ge.getPosition().x == x-i && ge.getPosition().y == y+16 )
						return ge;
				}
			}	
		} else if( direction == 2 ) {
			System.out.println("RIGHT");
			for (GameElement ge : l) {
				for( int i = 0; i <= 15; i++) {
					if( ge.getPosition().x == x+16 && ge.getPosition().y == y+i )
						return ge;
					if( ge.getPosition().x == x+16 && ge.getPosition().y == y-i )
						return ge;
				}
			}
		} else if( direction == 3 ) {
			for (GameElement ge : l) {
				for( int i = 0; i <= 15; i++) {
					if( ge.getPosition().x == x+i && ge.getPosition().y == y-16 )
						return ge;
					if( ge.getPosition().x == x-i && ge.getPosition().y == y-16 )
						return ge;
				}
			}
		} else if( direction == 4 ) {
			for (GameElement ge : l) {
				for( int i = 0; i <= 15; i++) {
					if( ge.getPosition().x == x-16 && ge.getPosition().y == y+i )
						return ge;
					if( ge.getPosition().x == x-16 && ge.getPosition().y == y-i )
						return ge;
				}
			}
		}
		return null;
	}*/
	
	public boolean getElementAt(float x, float y, int direction) {
		if( direction == 1 && (x%16 != 0)) {
			return true;
		} else if( direction == 1 && (x%16 == 0)) {
			for (GameElement ge : l) {
				if( ge.getPosition().x == x && ge.getPosition().y == y+16 ) {
					if(  ge != null && ge.getClass().getName().equals("models.Block") )
						return true;
				}	
			}	
		} else if( direction == 2 && (y%16 != 0)) {
			return true;
		} else if( direction == 2 && (y%16 == 0)) {
			for (GameElement ge : l) {
				if( ge.getPosition().x == x+16 && ge.getPosition().y == y ) {
					if(  ge != null && ge.getClass().getName().equals("models.Block") )
						return true;
				}	
			}
		}else if( direction == 3 && (x%16 != 0) ) {
			return true;
		} else if( direction == 3 && (x%16 == 0) ) {
			for (GameElement ge : l) {
				if( ge.getPosition().x == x && ge.getPosition().y == y-16 ) {
					if(  ge != null && ge.getClass().getName().equals("models.Block") )
						return true;
				}	
			}
		} else if( direction == 4  && (y%16 != 0)) {
			return true;
		} else if( direction == 4  && (y%16 == 0)) {
			for (GameElement ge : l) {
				if( ge.getPosition().x == x-16 && ge.getPosition().y == y ) {
					if(  ge != null && ge.getClass().getName().equals("models.Block") )
						return true;
				}	
			}
		}
		return false;
	}
	
	
	// Functions
	private void whichElement(int element, int x, int y) {
		switch (element) {
			case 0: l.add(new Pellet(new Vector2(x*16, y*16)));
					break;
			case 1: l.add(new Block(new Vector2(x*16, y*16)));
					break;
			case 2: l.add(new SuperPellet(new Vector2(x*16, y*16)));
					break;
		}
	}
	@Override
	public Iterator<GameElement> iterator() {
		return l.iterator();
	}
}
