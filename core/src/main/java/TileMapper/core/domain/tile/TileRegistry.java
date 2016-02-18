package TileMapper.core.domain.tile;

import TileMapper.core.domain.tile.Tile;

import java.util.Collection;
import java.util.Map;

public class TileRegistry
{
	private Map<String,Tile> registry;

	public TileRegistry(Map<String,Tile> map)
	{
		registry = map;
	}

	public Tile getTile(String id)
	{
		return registry.get(id);
	}

	public Collection<Tile> getTiles()
	{
		return registry.values();
	}

}
