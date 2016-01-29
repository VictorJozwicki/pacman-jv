package view;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import model.Block;
import model.GameElement;
import model.Pacman;

public class TextureFactory {
	static TextureFactory instance;
	HashMap<Class, Texture> hm = new HashMap<Class, Texture>();
	// Terrain
	Texture pellet, superPellet;
	// Pacman
	Texture pacmanBasic, pacmanLeft1, pacmanLeft2, pacmanRight1, pacmanRight2, pacmanUp1, pacmanUp2, pacmanDown1, pacmanDown2;
	// Constructor
	TextureFactory() {
		super();
		// Terrain
		// Sortir tout ça
		hm.put(Block.class, new Texture("block.png"));
		hm.put(Pacman.class, new Texture("pacman-3.png"));
		//hm.put("class model.Pellet", new Texture("pellet.png"));
		
		//block = new Texture("block.png");
		superPellet = new Texture("superpellet.png");
		// Pacman
		pacmanBasic = new Texture("pacman-3.png");
		pacmanLeft1 = new Texture("pacmanLeft.png");
		pacmanLeft2= new Texture("pacmanLeft-2.png");
		pacmanRight1 = new Texture("pacmanRight.png");
		pacmanRight2 = new Texture("pacmanRight-2.png");
		pacmanUp1 = new Texture("pacmanUp.png");
		pacmanUp2 = new Texture("pacmanUp-2.png");
		pacmanDown1 = new Texture("pacmanDown.png");
		pacmanDown2 = new Texture("pacmanDown-2.png");
	}
	
	
	public Texture getTexture(GameElement ge) {
		return hm.get(ge.getClass());
	}
	
	
	///////////////////////////////////////////////
	// -------------------------------------------- //	
	//					- Terrain -					//
	/*public Texture getBlockTexture() {
		return block;
	}*/
	//					- /Terrain/ -				//
	// -------------------------------------------- //	
	//					- Pacman -					//
	// Left
	public Texture getPacmanTextureLeft1() {
		return pacmanLeft1;
	}
	public Texture getPacmanTextureLeft2() {
		return pacmanLeft2;
	}
	// Right
	public Texture getPacmanTextureRight1() {
		return pacmanRight1;
	}
	public Texture getPacmanTextureRight2() {
		return pacmanRight2;
	}
	// Up
	public Texture getPacmanTextureUp1() {
		return pacmanUp1;
	}
	public Texture getPacmanTextureUp2() {
		return pacmanUp2;
	}
	// Down
	public Texture getPacmanTextureDown1() {
		return pacmanDown1;
	}
	public Texture getPacmanTextureDown2() {
		return pacmanDown2;
	}
	public Texture getPacmanBasic() {
		return pacmanBasic;
	}
	
	//					- /Pacman/ -				//
	// -------------------------------------------- //
	public static TextureFactory getInstance() {
		if (instance == null)
			return instance = new TextureFactory();
		return instance;
	}
}
