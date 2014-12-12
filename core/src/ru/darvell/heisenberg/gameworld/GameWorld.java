package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import ru.darvell.heisenberg.gameobjects.Heisenberg;
import ru.darvell.heisenberg.gameobjects.Platform;

import java.util.ArrayList;

/**
 * Created by darvell on 08.12.14.
 */
public class GameWorld {

	private Heisenberg heisenberg;
	private ArrayList<Platform> platforms;
	TiledMap map;

	public GameWorld(int midPointY, TiledMap map){
		heisenberg = new Heisenberg(100, 100, 32, 64);
		platforms = new ArrayList<Platform>();
		this.map = map;
		loadPlatforms();

	}

	public void update(float delta){
		heisenberg.update(delta);
	}

	public Heisenberg getHeisenberg(){
		return this.heisenberg;
	}

	private void loadPlatforms(){
		TiledMapTileLayer platformsLayer = (TiledMapTileLayer) map.getLayers().get("main");
		for (int i=0; i<platformsLayer.getWidth(); i++){
			for (int j=0; j<platformsLayer.getHeight(); j++){
				if (platformsLayer.getCell(i, j) != null){
					Platform platform = new Platform(i*32, j*32);
					platforms.add(platform);
//					System.out.println("add platform");
				}
			}
		}
	}
}
