package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import ru.darvell.heisenberg.gameobjects.Bullet;
import ru.darvell.heisenberg.gameobjects.Heisenberg;
import ru.darvell.heisenberg.helpers.AssetLoader;


/**
 * Класс отрисовывает картинку
 */
//TODO документировать
public class GameRender {

	private GameWorld gameWorld;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	private Heisenberg heisenberg;
	private TiledMapRenderer tiledMapRenderer;
	Box2DDebugRenderer box2dRender;
	private MapProperties mapProp;


	private int mapHeight;
	private int mapWidth;

	public GameRender(GameWorld gameWorld, TiledMap tiledMap){
		this.gameWorld = gameWorld;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 100, 80);
//		camera.setToOrtho(false, 800, 600);


		batcher = new SpriteBatch();

		batcher.setProjectionMatrix(camera.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);

		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
		mapProp = tiledMap.getProperties();
		box2dRender = new Box2DDebugRenderer();

		mapWidth = mapProp.get("width", Integer.class) * 32;
		mapHeight = mapProp.get("height", Integer.class) * 32;
		System.out.println(mapWidth+" : "+mapHeight);

		initGameObjects();
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(29/255.0f, 166/255.0f, 249/255.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		Gdx.app.log("GameRenderer", "render");


		box2dRender.render(gameWorld.get2dBWorld(), camera.combined);
		gameWorld.get2dBWorld().step(delta, 4, 4);
//		batcher.begin();
//
//		batcher.draw(AssetLoader.map,0,0,800*2,400*2);
//
//		for(Bullet bullet : gameWorld.getBullets()){
//			if (bullet.getStatus()){
//				batcher.draw(AssetLoader.bullet_r, bullet.getX()*10-150, bullet.getY()*10-145, 5, 3);
//			}
//		}
//		batcher.draw(AssetLoader.textur, heisenberg.getX()*10-150, heisenberg.getY()*10-170, heisenberg.getWidth(), heisenberg.getHeight());
//		batcher.end();
//
//
////		tiledMapRenderer.setView(camera);
////		tiledMapRenderer.render();
//
//		setPositionCamera(heisenberg.getPosition());
//		camera.update();
//		batcher.setProjectionMatrix(camera.combined);



	}

	private void initGameObjects() {
		heisenberg = gameWorld.getHeisenberg();
	}

	private void initAssets() {
//		bg = AssetLoader.bg;
//		grass = AssetLoader.grass;
//		birdAnimation = AssetLoader.birdAnimation;
//		birdMid = AssetLoader.bird;
//		birdDown = AssetLoader.birdDown;
//		birdUp = AssetLoader.birdUp;
//		skullUp = AssetLoader.skullUp;
//		skullDown = AssetLoader.skullDown;
//		bar = AssetLoader.bar;
	}

	private void setPositionCamera(Vector2 pos){
		if (pos.x < 40){
			camera.position.x = 400;
		}else{
			camera.position.x = pos.x*10;
		}
//		System.out.println(pos.y);
		if(pos.y < 30){
			camera.position.y = 300;
		}else {
			camera.position.y = pos.y * 10;
		}
	}
}
