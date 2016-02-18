package TileMapper.core.domain.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import TileMapper.core.domain.enums.DungeonType;
import TileMapper.core.domain.enums.TileType;

public class Tile {
	private final String id;
	private final String textureID;
	private final TileType tileType;
	private final DungeonType dungeonType;
	private final int blockingVal;
	private final boolean topBlocking, leftBlocking, rightBlocking, bottomBlocking;
	private final Texture texture;
	private final Image image;

	public final static int TILE_SIZE = 35;


	public Tile(String id, String textureID, TileType tileType, DungeonType dungeonType,
				  int blockingVal, Texture texture, Image image) {
		super();
		this.id = id;
		this.textureID = textureID;
		this.tileType = tileType;
		this.texture = texture;
		this.blockingVal = blockingVal;
		this.dungeonType = dungeonType;
		this.image = image;

		if(blockingVal == 1)
		{
			topBlocking = true; leftBlocking = true; rightBlocking = true; bottomBlocking = true;
		}
		else
		{
			topBlocking = false; leftBlocking = false; rightBlocking = false; bottomBlocking = false;
		}
	}

	public Tile(String id, String textureID, TileType tileType, DungeonType dungeonType,
				 int blockingVal, Texture texture, boolean top, boolean bottom, boolean left, boolean right, Image image) {
		super();
		this.id = id;
		this.textureID = textureID;
		this.tileType = tileType;
		this.texture = texture;
		this.blockingVal = blockingVal;
		this.dungeonType = dungeonType;
		this.image = image;

		topBlocking = top; leftBlocking = left; rightBlocking = right; bottomBlocking = bottom;
	}

	public String getId() {
		return id;
	}
	public String getTextureID() {
		return textureID;
	}
	public TileType getTileType() {
		return tileType;
	}
	public int getBlockingVal(){return blockingVal;	}

	public boolean isBlockedTile()
	{
		return blockingVal == 1;
	}

	public boolean isBottomBlocking()
	{
		return bottomBlocking;
	}

	public boolean isLeftBlocking()
	{
		return leftBlocking;
	}

	public boolean isRightBlocking()
	{
		return rightBlocking;
	}

	public boolean isTopBlocking()
	{
		return topBlocking;
	}

	public Texture getTexture() {
		return texture;
	}

	public DungeonType getDungeonType()
	{
		return dungeonType;
	}

	public Image getImage()
	{
		return image;
	}
}
