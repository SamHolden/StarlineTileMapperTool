package TileMapper.core.domain.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Sam on 15/02/2016.
 */
public class TileIcon extends Image
{
    private String tileID;

    public TileIcon(String id, Texture tex)
    {
        super(tex);
        tileID = id;
    }

    public String getTileID()
    {
        return tileID;
    }

    public void setTileID(String tileID)
    {
        this.tileID = tileID;
    }


}
