package TileMapper.core.util;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TileMapper.core.domain.tile.Tile;
import TileMapper.core.domain.enums.DungeonType;
import TileMapper.core.domain.enums.TileType;
import TileMapper.core.domain.exceptions.TextureNotFoundException;

import com.badlogic.gdx.graphics.Texture;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil
{
	public static Document loadXMLDocument(URL url) throws DocumentException
	{
		SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
	}
	
	public static Map<String,Tile> parseXMLIntoTiles(Document doc, Map<String, Texture> textureRegistry) throws TextureNotFoundException
	{
		Map<String,Tile> payloadMap = new HashMap<String, Tile>();

		Element root = doc.getRootElement();
		List<Element> elementsList = root.elements();

		String id, textureId;
		TileType tileType;
		DungeonType dungeonType;
		boolean topBlocking, leftBlocking, rightBlocking, bottomBlocking;
		int blockingVal;
		Tile tile;
		Texture texture;

		for(Element element : elementsList)
		{
			id = element.attribute("id").getValue();
			textureId = element.element("texture_id").getText();
			tileType = TileType.valueOf(element.element("tile_type").getText());
			blockingVal = Integer.parseInt(element.element("blocking").getText());
			dungeonType = DungeonType.valueOf(element.element("dungeon_type").getText());

			texture = textureRegistry.get(id);

			if(texture == null)
			{
				throw new TextureNotFoundException(id);
			}

			if(blockingVal == 2)
			{
				Element blockingSides = element.element("sides");
				topBlocking = Boolean.getBoolean(blockingSides.element("top").getText());
				bottomBlocking = Boolean.getBoolean(blockingSides.element("bottom").getText());
				leftBlocking = Boolean.getBoolean(blockingSides.element("left").getText());
				rightBlocking = Boolean.getBoolean(blockingSides.element("right").getText());

				tile = new Tile(id, textureId, tileType, dungeonType, blockingVal, texture, topBlocking, bottomBlocking, leftBlocking, rightBlocking);
				payloadMap.put(id, tile);
			}
			else
			{
				tile = new Tile(id, textureId, tileType, dungeonType, blockingVal, texture);
				payloadMap.put(id, tile);
			}
		}
		return payloadMap;
	}
}
