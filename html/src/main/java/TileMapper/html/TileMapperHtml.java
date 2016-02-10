package TileMapper.html;

import TileMapper.core.TileMapper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class TileMapperHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new TileMapper();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
