package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import ru.darvell.heisenberg.gameobjects.Heisenberg;
import ru.darvell.heisenberg.gameobjects.Platform;

import java.util.ArrayList;

/**
 * Игровой мир со всеми объектами
 */

//TODO Продумать генерацию платформ. Что то не совсем корректно генерируются объекты.
//TODO документировать
public class GameWorld {

	private Heisenberg heisenberg;
	private ArrayList<Platform> platforms;
	private  boolean isGrounded;
	World world;

	TiledMap map;

	public GameWorld(int midPointY, TiledMap map){
//		heisenberg = new Heisenberg(100, 100, 32, 64);
		platforms = new ArrayList<Platform>();
		this.map = map;


		//Инициализация Box2D
		world = new World(new Vector2(0, -20), true);
		//Создаем тело игроку
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;

		Body bodyPlayer = world.createBody(def);
		heisenberg = new Heisenberg(bodyPlayer);
		heisenberg.getBody().setTransform(120f, 100f, 0);
		heisenberg.getBody().setFixedRotation(true);
		loadPlatforms();
	}

	public void update(float delta){
		heisenberg.update(delta);
		heisenberg.setGrounded(isPlayerGrounded(delta));
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
					platform.getBody().setTransform(i*32, j*32, 0);
//					System.out.println("add platform");
				}
			}
		}
	}

	private boolean isPlayerGrounded(float deltaTime) {
		Array<Contact> contactList = world.getContactList();
		for(int i = 0; i < contactList.size; i++) {
			Contact contact = contactList.get(i);
			if(contact.isTouching() && (contact.getFixtureA() == heisenberg.playerSensorFixture ||
					contact.getFixtureB() == heisenberg.playerSensorFixture)) {

				Vector2 pos = heisenberg.getPosition();
				WorldManifold manifold = contact.getWorldManifold();
				boolean below = true;
				for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
					below &= (manifold.getPoints()[j].y < pos.y - 1.5f);
				}

				if(below) {
//					if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("p")) {
//						groundedPlatform = (MovingPlatform)contact.getFixtureA().getBody().getUserData();
//					}
//
//					if(contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("p")) {
//						groundedPlatform = (MovingPlatform)contact.getFixtureB().getBody().getUserData();
//					}
					return true;
				}

				return false;
			}
		}
		return false;
	}
}
