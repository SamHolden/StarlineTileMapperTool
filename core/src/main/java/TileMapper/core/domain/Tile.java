package TileMapper.core.domain;

import TileMapper.core.domain.enums.DungeonType;
import com.badlogic.gdx.graphics.Texture;

import TileMapper.core.domain.enums.TileType;

public class Tile {
	private final String id;
	private final String textureID;
	private final TileType tileType;
	private final DungeonType dungeonType;
	private final int blockingVal;
	private final boolean topBlocking, leftBlocking, rightBlocking, bottomBlocking;
	private final Texture texture;


	public Tile(String id, String textureID, TileType tileType, DungeonType dungeonType,
				  int blockingVal, Texture texture) {
		super();
		this.id = id;
		this.textureID = textureID;
		this.tileType = tileType;
		this.texture = texture;
		this.blockingVal = blockingVal;
		this.dungeonType = dungeonType;

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
				 int blockingVal, Texture texture, boolean top, boolean bottom, boolean left, boolean right) {
		super();
		this.id = id;
		this.textureID = textureID;
		this.tileType = tileType;
		this.texture = texture;
		this.blockingVal = blockingVal;
		this.dungeonType = dungeonType;

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
}
