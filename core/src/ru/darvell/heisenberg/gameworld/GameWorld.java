package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import ru.darvell.heisenberg.gameobjects.Bullet;
import ru.darvell.heisenberg.gameobjects.Heisenberg;
import ru.darvell.heisenberg.gameobjects.Platform;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Игровой мир со всеми объектами
 */

//TODO документировать
public class GameWorld {

	final public static short CATEGORY_HEISENBERG = 0x0001;
	final public static short CATEGORY_BLOCK = 0x0002;

	final public static short MASK_PLAYER = CATEGORY_HEISENBERG | CATEGORY_BLOCK;
	final public static short MASK_BLOCK = CATEGORY_HEISENBERG | CATEGORY_BLOCK;

	private Heisenberg heisenberg;

	private ArrayList<Platform> platforms;
	private LinkedList<Bullet> bullets;

	private  boolean isGrounded;


	World world;

	TiledMap map;

	public GameWorld(int midPointY, TiledMap map){
//		heisenberg = new Heisenberg(100, 100, 32, 64);
		platforms = new ArrayList<Platform>();
		this.map = map;


		//Инициализация Box2D
		world = new World(new Vector2(0, -98f), true);
		//Создаем тело игроку
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;

		Body bodyPlayer = world.createBody(def);
		heisenberg = new Heisenberg(bodyPlayer);
		heisenberg.getBody().setTransform(30f, 30f, 0);
		heisenberg.getBody().setFixedRotation(true);
		loadPlatforms();
		bullets = new LinkedList<Bullet>();
		world.setContactFilter(new GameContactFilter());
	}


	public Heisenberg getHeisenberg(){
		return this.heisenberg;
	}



	public World get2dBWorld(){
		return world;
	}

	private void loadPlatforms(){
		TiledMapTileLayer platformsLayer = (TiledMapTileLayer) map.getLayers().get("main");
		for (int i=0; i<platformsLayer.getWidth(); i++){
			for (int j=0; j<platformsLayer.getHeight(); j++){
				if (platformsLayer.getCell(i, j) != null){
					BodyDef def = new BodyDef();
					def.type = BodyDef.BodyType.StaticBody;
					Body tmpBody = world.createBody(def);
					Platform platform = new Platform(tmpBody);
					platforms.add(platform);
//					System.out.println(i+":"+j);
					platform.getBody().setTransform(i*Platform.WIDTH+16, j*Platform.HEIGHT+16, 0);
//					System.out.println("add platform");
				}
			}
		}
	}

	public void createBullet(){
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		Body tmpBody = world.createBody(def);
		Bullet bullet = new Bullet(tmpBody, 1);
		bullet.getBody().setTransform(31f, 30f, 0);
		bullets.addLast(bullet);
	}

	public void updateBullets(){
		for (Bullet bullet : bullets){
			bullet.update();
		}
	}



}
