package TileMapper.core;

import TileMapper.core.domain.Tile;
import TileMapper.core.domain.exceptions.TextureNotFoundException;
import TileMapper.core.util.FileHandler;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import TileMapper.core.util.XMLUtil;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.Map;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.dom4j.Document;
import org.dom4j.DocumentException;

public class TileMapper implements ApplicationListener 
{
	Map<String, Texture> textureRegistry;
	Map<String, Tile> tileRegistry;

	Stage stage;
	Table table;

	@Override
	public void create () 
	{
		loadResources();
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
		stage.dispose();
	}

	private void loadResources()
	{
		try
		{
			//load textures
			Document dungeonTextureDoc = XMLUtil.loadXMLDocument(FileHandler.getAssetsFileURL("dungeon_textures.xml"));
			textureRegistry = FileHandler.produceTextureRegistry(dungeonTextureDoc);

			//load dungeon tiles
			Document dungeonTileDoc = XMLUtil.loadXMLDocument(FileHandler.getAssetsFileURL("dungeon_tiles.xml"));
			tileRegistry = XMLUtil.parseXMLIntoTiles(dungeonTileDoc, textureRegistry);
		}
		catch(MalformedURLException e)
		{
			Gdx.app.error("ERROR", "An error has occurred loading the resource files");
			e.printStackTrace();
		}
		catch(DocumentException e)
		{
			Gdx.app.error("ERROR", "An error has occurred during the parsing of the XML Document");
			e.printStackTrace();
		}
		catch(TextureNotFoundException e)
		{
			Gdx.app.error("ERROR", e.getMessage());
		}
	}
}
