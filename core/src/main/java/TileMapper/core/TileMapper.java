package TileMapper.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import TileMapper.core.util.XMLUtil;

import java.net.URL;
import java.net.MalformedURLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public class TileMapper implements ApplicationListener 
{

	@Override
	public void create () 
	{
		try
		{
			Document doc = XMLUtil.loadXMLDocument(new URL("assets/dungeon_tiles.xml"));
		}
		catch(MalformedURLException e)
		{
			Gdx.app.error("ERROR", "The Dungeon Tile file does not exist in the assets directory");
			e.printStackTrace();
		}
		catch(DocumentException e)
		{
			Gdx.app.error("ERROR", "An error has occurred during the parsing of the XML Document");
			e.printStackTrace();
		}
		
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
