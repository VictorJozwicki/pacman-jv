package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

import utility.Node;

public class Maze implements Iterable<GameElement> {
	private ArrayList<GameElement> l = new ArrayList<GameElement>();
	private int height;
	public int numberPellets = 0;
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost)
				return +1; // Up in the list
			if (n1.fCost > n0.fCost)
				return -1; // Down in the list
			return 0;
		}
	};
	int[][] mazeLevel = new int[][] { // 0 is pellet, 1 is block, 2 is super pellet, 3 is ghosts, 4 is pacman, 5 is void
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
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 5, 5, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 5, 5, 5, 5, 5, 5, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5, 3, 3, 3, 3, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
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
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	// Constuctors
	public Maze() {
		super();
		System.out.println("Building : maze");
		height = mazeLevel.length;
		for (int y = 0; y <= mazeLevel.length - 1; y++) {
			height--;
			for (int i = 0; i <= mazeLevel[height].length - 1; i++)
				this.whichElement(mazeLevel[height][i], i, y);
		}
		System.out.println("Building : maze COMPLETED");
	}

	public GameElement getElementAt(float x, float y) {
		for (GameElement ge : l)
			if (ge.getPosition().x == x && ge.getPosition().y == y)
				return ge;
		return null;
	}

	public void deleteElementAt(GameElement geToDelete) {
		for (GameElement ge : l) {
			if (ge.getPosition().x == geToDelete.getPosition().x && ge.getPosition().y == geToDelete.getPosition().y) {
				l.remove(geToDelete);
				GameElement newGameElem = new GameElement();
				newGameElem.setPosition(new Vector2(geToDelete.getPosition().x, geToDelete.getPosition().y));
				l.add(newGameElem);
				break;
			}
		}
	}

	public ArrayList<Node> findPath(Vector2 start, Vector2 goal) { // Pathfinding A*
		ArrayList<Node> openList = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			// Order the list to the closest of the goal
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(goal)) { // If found a path
				ArrayList<Node> path = new ArrayList<Node>();
				while( current.parent != null ) {
					path.add(current);
					current = current.parent;
				}
				openList.clear(); // We just clear for memory purpose
				closedList.clear(); // We just clear for memory purpose
				return path; // Return the path with nodes
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {									// 0 1 2
				if ( i == 0 || i == 2 || i == 4 || i == 6 || i == 8 )		// 3 4 5
					continue; // Middle OR diagonals, so we skip			// 6 7 8
				float x = current.tile.x;
				float y = current.tile.y;
				int xi = (i % 3) - 1;
				int yi = ((i / 3) - 1 ) * (-1);
				GameElement at = getElementAt(x + xi*16, y + yi*16);
				if( at == null ) continue; // If it's null we don't want it
				if( at.getClass().equals(Block.class)) continue; // If it's a block we don't want it
				Vector2 a = new Vector2(x + xi*16, y + yi*16);
				// Calculate the cost from the node to the goal (goes higher if you go away from the goal)
				double gCost = current.gCost + getDistance(current.tile, a);
				double hCost = getDistance(a, goal);
				// Create the new Node to add
				Node node = new Node(a, current, gCost, hCost);
				// Here basically, if the node is allready in the closed list, we don't need to add it again
				if( vecInList(closedList, a) && gCost > node.gCost ) continue;
				 // If it's not in the list, add it
				if( !vecInList(openList, a) || gCost < node.gCost ) openList.add(node);
			}
		}
		closedList.clear(); // We just clear for memory purpose
		return null; // If there is no path found
	} // Pathfinding A*
	
	private boolean vecInList(ArrayList<Node> list, Vector2 vector) {
		for( Node n : list)
			if( n.tile.x == vector.x && n.tile.y == vector.y ) return true;
		return false;
	}

	// Functions
	private double getDistance(Vector2 tile, Vector2 goal) {
		double dx = tile.x - goal.x;
		double dy = tile.y - goal.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	private void whichElement(int element, int x, int y) {
		switch (element) {
		case 0:
			l.add(new Pellet(new Vector2(x * 16, y * 16)));
			numberPellets++;
			break;
		case 1:
			l.add(new Block(new Vector2(x * 16, y * 16)));
			break;
		case 2:
			l.add(new SuperPellet(new Vector2(x * 16, y * 16)));
			numberPellets++;
			break;
		}
	}

	@Override
	public Iterator<GameElement> iterator() {
		return l.iterator();
	}
}
