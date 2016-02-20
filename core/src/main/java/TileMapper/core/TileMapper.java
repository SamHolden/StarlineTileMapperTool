package TileMapper.core;

import java.net.MalformedURLException;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import TileMapper.core.domain.exceptions.TextureNotFoundException;
import TileMapper.core.domain.tile.Tile;
import TileMapper.core.domain.tile.TileIcon;
import TileMapper.core.domain.tile.TileMap;
import TileMapper.core.domain.tile.TileRegistry;
import TileMapper.core.domain.tile.TileSelectedListener;
import TileMapper.core.util.FileHandler;
import TileMapper.core.util.XMLUtil;

public class TileMapper implements ApplicationListener
{
	private final int UI_WIDTH = 192;
    private int VIEWPORT_WIDTH;
	private final int DEFAULT_MAP_WIDTH = 200;
	private final int DEFAULT_MAP_HEIGHT = 200;

    private int cameraX = 0, cameraY = 0;

	Map<String, Texture> textureRegistry;
	TileRegistry tileRegistry;

	Stage stage;
	Skin skin;

	Image uiImage;

	private Table uiTable;
	private Table tileTable;
	private Group ui;
	private Group viewport;

	private Tile activeTile;
	private TileMap activeTileMap;

	@Override
	public void create ()
	{
		loadResources();

		stage = new Stage(new ScreenViewport());
		activeTileMap = new TileMap(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT, tileRegistry);

		//setup viewport
		viewport = new Group();
        VIEWPORT_WIDTH = Gdx.graphics.getWidth() - UI_WIDTH;
		viewport.setSize(VIEWPORT_WIDTH, Gdx.graphics.getHeight());
		viewport.setPosition(UI_WIDTH, 0);

		//populate viewport with images
        tileTable = new Table();
        tileTable.setSize(VIEWPORT_WIDTH, Gdx.graphics.getHeight());
        tileTable.align(Align.left | Align.top);
        tileTable.setPosition(0, 0);
        populateViewportTileMap();
        viewport.addActor(tileTable);

		//setup UI
		ui = new Group();
		ui.setSize(UI_WIDTH, Gdx.graphics.getHeight());
		ui.setPosition(0,0);

		ui.addActor(uiImage);

		//create table to hold tiles in toolbar
		uiTable = new Table();
		uiTable.setSize(UI_WIDTH, Gdx.graphics.getHeight());
		uiTable.align(Align.left | Align.top);
		uiTable.setPosition(0, 0);
		ui.addActor(uiTable);

		populateTileTable();

		//add groups to stage
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
			tileRegistry = new TileRegistry(XMLUtil.parseXMLIntoTiles(dungeonTileDoc, textureRegistry));

			skin = new Skin(Gdx.files.internal("assets/ui/uiskin.json"));

			uiImage = new Image(new Texture(Gdx.files.internal("assets/textures/planet_ui.jpg")));

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

		for(Tile tile: tileRegistry.getTiles())
		{
			icon = new TileIcon(tile.getId(),tile.getTexture());

			icon.addListener(new TileSelectedListener(tile.getId()){
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					super.clicked(event, x, y);
					activeTile = tileRegistry.getTile(this.id);
					Gdx.app.log("Tile Selected", activeTile.getId());
				}
			});

			uiTable.add(icon);
		}
	}

    private void populateViewportTileMap()
    {
        TileIcon tileIcon;

        System.out.println(VIEWPORT_WIDTH + " " + Tile.TILE_SIZE + " " + VIEWPORT_WIDTH/Tile.TILE_SIZE);

        for(int y = 0; y < viewport.getHeight()/Tile.TILE_SIZE; y++)
        {
            for(int x = 0; x < VIEWPORT_WIDTH/Tile.TILE_SIZE; x++)
            {
                tileIcon = new TileIcon("null",textureRegistry.get("null"));

                tileTable.add(tileIcon);
            }
            tileTable.row();
        }
    }
}
