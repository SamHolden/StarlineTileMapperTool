package TileMapper.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;


public class TileMapper implements ApplicationListener {

	@Override
	public void create () 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
	    factory.setIgnoringElementContentWhitespace(true);
	    try 
	    {
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        File file = Gdx.files.internal("assets/dungeon_tiles.xml").file();
	        Document doc = builder.parse(file);
	        // Do something with the document here.

	    } 
	    catch (ParserConfigurationException e) 
	    {
	    	
	    } 
	    catch (SAXException e) 
	    {
	    	
	    } 
	    catch (IOException e) 
	    { 
	    	
	    }
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.app.log("tag", "more tag");
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
