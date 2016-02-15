package TileMapper.core.domain;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Sam on 15/02/2016.
 */
public class TileSelectedListener extends ClickListener
{
    protected String id;

    public TileSelectedListener(String newID)
    {
        id = newID;
    }
}
