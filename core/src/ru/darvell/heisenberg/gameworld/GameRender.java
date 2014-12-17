package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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

	public GameRender(GameWorld gameWorld, TiledMap tiledMap){
		this.gameWorld = gameWorld;
		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 100, 80);
		camera.setToOrtho(false, 800, 600);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(camera.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);

		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
		box2dRender = new Box2DDebugRenderer();

		initGameObjects();
	}

	public void render(float delta) {
//		Gdx.app.log("GameRenderer", "render");



//		camera.update();
//		tiledMapRenderer.setView(camera);
//		tiledMapRenderer.render();
//		batcher.begin();
//		batcher.draw(AssetLoader.textur, heisenberg.getX(), heisenberg.getY(), heisenberg.getWidth(), heisenberg.getHeight());
//		batcher.end();

		tiledMapRenderer.render();
		tiledMapRenderer.setView(camera);
		box2dRender.render(gameWorld.get2dBWorld(), camera.combined);

		gameWorld.get2dBWorld().step(delta, 4, 4);


		batcher.begin();
		batcher.draw(AssetLoader.textur, heisenberg.getX()*10-150, heisenberg.getY()*10-170, heisenberg.getWidth(), heisenberg.getHeight());
		batcher.end();
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
}
