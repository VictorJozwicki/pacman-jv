package utility;

import com.badlogic.gdx.math.Vector2;

public class Node {
	public Vector2 tile;
	public Node parent;
	public double fCost, gCost, hCost;

	public Node(Vector2 tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}
}