package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import ru.darvell.heisenberg.gameobjects.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Игровой мир со всеми объектами
 */

//TODO документировать
public class GameWorld {

	final public static short CATEGORY_HEISENBERG = 0x0001;
	final public static short CATEGORY_HEIS_LEGS = 0x0012;
	final public static short CATEGORY_BLOCK = 0x0002;
	final public static short CATEGORY_BULLET_PLAYER = 0x0004;
	final public static short CATEGORY_BULLET_ENEMY = 0x0008;
	final public static short CATEGORY_ENEMY = 0x00010;


	final public static short MASK_HEISENBERG = CATEGORY_BLOCK | CATEGORY_BULLET_ENEMY;
	final public static short MASK_HEISLEGS = CATEGORY_BLOCK;
	final public static short MASK_BLOCK = CATEGORY_HEISENBERG;
	final public static short MASK_BULLET_PLAYER = CATEGORY_BLOCK;
	final public static short MASK_BULLET_ENEMY = CATEGORY_BLOCK;
	final public static short MASK_ENEMY = CATEGORY_BULLET_PLAYER | CATEGORY_BLOCK;

	private Heisenberg heisenberg;

	private Array<Platform> platforms;
	private Array<Bullet> bullets;
	private Array<Enemy> enemies;


	private  boolean isGrounded;


	World world;

	TiledMap map;

	public GameWorld(int midPointY, TiledMap map){
//		heisenberg = new Heisenberg(100, 100, 32, 64);
		platforms = new Array<Platform>();
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
		bullets = new Array<Bullet>();
		enemies = new Array<Enemy>();
		createEnemy();

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

	public void createBulletPlayer(){
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		Body tmpBody = world.createBody(def);
		PlayerBullet bullet = new PlayerBullet(tmpBody, heisenberg.getDirection());
		Vector2 position = heisenberg.getPosition();
		position.x+=2f * heisenberg.getDirection();
		position.y+=1f;
		bullet.getBody().setTransform(position.x, position.y, 0);
		bullets.add(bullet);
	}

	public void createBulletEnemy(Enemy enemy){
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		Body tmpBody = world.createBody(def);
		EnemyBullet bullet = new EnemyBullet(tmpBody, enemy.getFace());
		Vector2 position = enemy.getPosition();
		position.x+=2f * enemy.getFace();
		position.y+=1f;
		bullet.getBody().setTransform(position.x, position.y, 0);
		bullets.add(bullet);
	}

	public void createEnemy(){
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		Body tmpBody = world.createBody(def);
		Enemy tmpEnemy = new Enemy(tmpBody);
		tmpEnemy.getBody().setTransform(50f, 30f, 0);
		tmpEnemy.getBody().setFixedRotation(true);
		enemies.add(tmpEnemy);
	}

	public void updateBullets(){
		for (Bullet bullet : bullets){
			bullet.update();
		}
	}

	public void delDeadBullets(){
		for (Bullet bullet : bullets){
			if (!bullet.getStatus()){
				world.destroyBody(bullet.getBody());
				bullets.removeValue(bullet, true);
			}
		}
	}

	public void updateEnemies(float delta){
		for (Enemy enemy: enemies){
			if (!enemy.getStatus()){
				world.destroyBody(enemy.getBody());
				enemies.removeValue(enemy, true);
			}else{
				enemy.update(delta, heisenberg.getPosition());
				if (enemy.getNeedBullet()){
					createBulletEnemy(enemy);
				}

			}
		}
	}

	public Array<Bullet> getBullets(){
		return bullets;
	}
}
