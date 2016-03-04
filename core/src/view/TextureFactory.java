package view;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import models.Block;
import models.GameElement;
import models.Pacman;
import models.Pellet;
import models.SuperPellet;
import models.ghosts.Blinky;
import models.ghosts.Clyde;
import models.ghosts.Inky;
import models.ghosts.Pinky;

public class TextureFactory {
	static TextureFactory instance;
	
	@SuppressWarnings("rawtypes")
	HashMap<Class, Texture> hm = new HashMap<Class, Texture>();

	// Constructor
	TextureFactory() {
		super();
		// Terrain, eventually put that in ""
		hm.put(Block.class, new Texture(Gdx.files.internal("snow_block.png"))); // Snow_Block
		hm.put(Pellet.class, new Texture(Gdx.files.internal("pellet.png")));
		hm.put(SuperPellet.class, new Texture(Gdx.files.internal("superpellet.png")));		
		
		// Characters
		hm.put(Pacman.class, new Texture(Gdx.files.internal("pacman-3.png")));
		hm.put(Blinky.class, new Texture(Gdx.files.internal("ghost1.png")));
		hm.put(Pinky.class, new Texture(Gdx.files.internal("ghost2.png")));
		hm.put(Inky.class, new Texture(Gdx.files.internal("ghost3.png")));
		hm.put(Clyde.class, new Texture(Gdx.files.internal("ghost4.png")));
	}

	//					- /Functions/ -					//
	// ------------------------------------------------ //
	public static TextureFactory getInstance() {
		if (instance == null)
			return instance = new TextureFactory();
		return instance;
	}
	
	public Texture getTexture(GameElement ge) {
		return hm.get(ge.getClass());
	}
	
	public void setOtherTexture( GameElement ge, String direction, String animation, String extension ) {
		if( ge != null && ge.getClass().equals(Pacman.class) && direction != null) {
			String textureName = null;
			if( animation != null )
				textureName = "pacman" + direction + animation + extension;
			else
				textureName = "pacman" + direction + extension;
			hm.replace(Pacman.class, new Texture(Gdx.files.internal(textureName)));
		}
		if( animation == "escaping" ) { // Really really ugly code
			hm.replace(Blinky.class, new Texture(Gdx.files.internal("ghostEscaping.png"))); // Really really ugly code
			hm.replace(Pinky.class, new Texture(Gdx.files.internal("ghostEscaping.png"))); // Really really ugly code
			hm.replace(Inky.class, new Texture(Gdx.files.internal("ghostEscaping.png"))); // Really really ugly code
			hm.replace(Clyde.class, new Texture(Gdx.files.internal("ghostEscaping.png"))); // Really really ugly code
		}
		if( animation == "ghostNormal" ) { // Really really ugly code
			hm.replace(Blinky.class, new Texture(Gdx.files.internal("ghost1.png"))); // Really really ugly code
			hm.replace(Pinky.class, new Texture(Gdx.files.internal("ghost2.png"))); // Really really ugly code
			hm.replace(Inky.class, new Texture(Gdx.files.internal("ghost3.png"))); // Really really ugly code
			hm.replace(Clyde.class, new Texture(Gdx.files.internal("ghost4.png"))); // Really really ugly code I KNOW
		}
		if( animation == "ghostDead" ) { // Really really ugly code
			hm.replace(ge.getClass(), new Texture(Gdx.files.internal("ghostDead.png"))); // Really really ugly code
		}
		if( animation == "blinkyNormal" ) { // Really really ugly code
			hm.replace(ge.getClass(), new Texture(Gdx.files.internal("ghost1.png"))); // Really really ugly code
		}
		if( animation == "clydeNormal" ) { // Really really ugly code
			hm.replace(ge.getClass(), new Texture(Gdx.files.internal("ghost4.png"))); // Really really ugly code
		}
		if( animation == "black" ) { // Really really ugly code
			hm.replace(ge.getClass(), new Texture(Gdx.files.internal("dark.png"))); // Really really ugly code
		}
		
	}
	
	// To modify one day
	public Texture getOtherTexture(String texture) {
		Texture tex = null;
		if(texture.equals("pacman"))
			tex = new Texture(Gdx.files.internal("icons/pacman128.png"));
		if(texture.equals("win"))
			tex = new Texture(Gdx.files.internal("win.png"));
		if(texture.equals("block"))
			tex = new Texture(Gdx.files.internal("block.png"));
		if(texture.equals("lose"))
			tex = new Texture(Gdx.files.internal("lose.png"));
		return tex;
	}
}
