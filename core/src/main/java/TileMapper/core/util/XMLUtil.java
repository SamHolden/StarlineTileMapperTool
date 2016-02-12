package TileMapper.core.util;

import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLUtil
{
	public static Document loadXMLDocument(URL url) throws DocumentException
	{
		SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
	}
	
	public static <T> List<T> parseXML(Document doc, Class<T> modelClass)
	{
		
	}
}
