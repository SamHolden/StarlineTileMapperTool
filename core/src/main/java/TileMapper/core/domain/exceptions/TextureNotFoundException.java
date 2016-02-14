package TileMapper.core.domain.exceptions;

/**
 * Created by Sam on 13/02/2016.
 */
public class TextureNotFoundException extends Exception
{
    String id;

    public TextureNotFoundException(String id)
    {
        this.id = id;
    }

    @Override
    public String getMessage()
    {
        return "The texture: " + id + " was not found in the texture registry";
    }
}
