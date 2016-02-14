package TileMapper.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import TileMapper.core.TileMapper;

public class TileMapperDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = "Starline TileMapper Tool";
		cfg.useGL30 = true;
		cfg.height = 1080;
		cfg.width = 1920;

		new LwjglApplication(new TileMapper(), cfg);
	}
}
