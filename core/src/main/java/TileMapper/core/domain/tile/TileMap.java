package TileMapper.core.domain.tile;

public class TileMap
{
	private int mapWidth, mapHeight;
	private Tile[][] map;
	TileRegistry tileRegistry;

	public TileMap(int width, int height, TileRegistry tileRegistry)
	{
		mapWidth = width;
		mapHeight = height;
		map = new Tile[mapWidth][mapHeight];
        this.tileRegistry = tileRegistry;
		generateVoidTileMap();
	}

	public TileMap generateVoidTileMap()
	{
		for(int x = 0; x < mapWidth; x++)
		{
			for(int y = 0; y < mapHeight; y++)
			{
				map[x][y] = tileRegistry.getTile("void");
			}

		}

		return this;
	}

	public void setTile(int x, int y, Tile tile)
	{
		map[x][y] = tile;
	}

	public Tile getTile(int x, int y)
	{
		return map[x][y];
	}

}
