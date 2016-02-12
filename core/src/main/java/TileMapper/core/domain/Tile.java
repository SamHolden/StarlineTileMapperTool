package TileMapper.core.domain;

import com.badlogic.gdx.graphics.Texture;

import TileMapper.core.domain.enums.TileType;

public class Tile {
	private final String id;
	private final String textureID;
	private final TileType tileType;
	private final boolean blocking;
	private final Texture texture;


	public Tile(String id, String textureID, TileType tileType,
			boolean blocking, Texture texture) {
		super();
		this.id = id;
		this.textureID = textureID;
		this.tileType = tileType;
		this.blocking = blocking;
		this.texture = texture;
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
	public boolean isBlocking() {
		return blocking;
	}
	public Texture getTexture() {
		return texture;
	}
}
