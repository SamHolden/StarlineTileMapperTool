package TileMapper.core;

import TileMapper.core.domain.Tile;
import TileMapper.core.domain.TileIcon;
import TileMapper.core.domain.TileSelectedListener;
import TileMapper.core.domain.exceptions.TextureNotFoundException;
import TileMapper.core.util.FileHandler;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import TileMapper.core.util.XMLUtil;

import java.net.MalformedURLException;
import java.util.Map;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.dom4j.Document;
import org.dom4j.DocumentException;

public class TileMapper implements ApplicationListener 
{
	private final int UI_WIDTH = 200;

	Map<String, Texture> textureRegistry;
	Map<String, Tile> tileRegistry;

	Stage stage;
	Skin skin;

	Image tempImage;

	private Table table;
	private Group ui;
	private Group viewport;

	private Tile activeTile;

	@Override
	public void create () 
	{
		loadResources();

		stage = new Stage(new ScreenViewport());

		viewport = new Group();
		viewport.addActor(tempImage);
		viewport.setSize(Gdx.graphics.getWidth()-UI_WIDTH, Gdx.graphics.getHeight());
		viewport.setPosition(200, 0);

		ui = new Group();
		ui.setSize(UI_WIDTH, Gdx.graphics.getHeight());
		ui.setPosition(0,0);

		table = new Table();
		table.setSize(stage.getWidth(), stage.getHeight());
		table.align(Align.left|Align.top);
		table.setPosition(0, 0);
		ui.addActor(table);



		populateTileTable();

		stage.addActor(viewport);
		stage.addActor(ui);
		Gdx.input.setInputProcessor(stage);
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

			skin = new Skin(Gdx.files.internal("assets/ui/uiskin.json"));

			tempImage = new Image(new Texture(Gdx.files.internal("assets/textures/planet.jpg")));

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

	private void populateTileTable()
	{
		TileIcon icon;

		for(Tile tile: tileRegistry.values())
		{
			icon = new TileIcon(tile.getId(),tile.getTexture());

			icon.addListener(new TileSelectedListener(tile.getId()){
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					super.clicked(event, x, y);
					activeTile = tileRegistry.get(this.id);
					Gdx.app.log("Tile Selected", activeTile.getId());
				}
			});

			table.add(icon);
		}
	}
}
