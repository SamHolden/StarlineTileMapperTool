package TileMapper.core.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import TileMapper.core.domain.tile.TileMap;

/**
 * Created by Sam on 13/02/2016.
 */
public class FileHandler
{
	private static String textureFileLocation = "assets/textures/";
	private static String textureFileTypeExtension = ".png";

    public static URL getAssetsFileURL(String fileName) throws MalformedURLException
    {
        return Gdx.files.internal("assets/" + fileName).file().toURI().toURL();
    }

    public static boolean exportTileMapToFile()
    {
    	return true;
    }

    public static Map<String, Texture> produceTextureRegistry(Document doc)
    {
        Element root = doc.getRootElement();

        List<Element> elementList = root.elements();
        String textureID;
        String fileURL;
        Texture texture;

        Map<String, Texture> textureRegistry = new HashMap<String, Texture>();

        for(Element element: elementList)
        {
            textureID = element.getText();
            fileURL = textureFileLocation + textureID + textureFileTypeExtension;
            texture = new Texture(fileURL);

            textureRegistry.put(textureID, texture);
        }

        return textureRegistry;
    }

    private static TileMap loadTileMapFromFile()
    {
    	return null;
    }
}
