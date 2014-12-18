package ru.darvell.heisenberg.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import ru.darvell.heisenberg.HeisenbergGame;
import ru.darvell.heisenberg.gameworld.GameRender;
import ru.darvell.heisenberg.gameworld.GameWorld;
import ru.darvell.heisenberg.gameworld.WorldController;
import ru.darvell.heisenberg.helpers.InputHandler;

/**
 * Тестовый уровень. Для того, чтобы
 */
//TODO документировать
public class TestLevel implements Screen, InputProcessor {



	final HeisenbergGame heisenbergGame;
	private GameWorld gameWorld;
	private GameRender gameRender;
	private WorldController controller;

	public int width;
	public int height;

	private float runTime = 0;

	private TiledMap tiledMap;



	public TestLevel(final HeisenbergGame hsg){
		tiledMap = new TmxMapLoader().load("data/maps/testmap.tmx");
//		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);


		heisenbergGame = hsg;

		gameWorld = new GameWorld(Gdx.graphics.getHeight(), tiledMap);

		gameRender = new GameRender(gameWorld, tiledMap);
		controller = new WorldController(gameWorld);
		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		controller.update(delta);
		gameRender.render(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {

		switch (keycode){
			case Input.Keys.LEFT:
				controller.resetWay();
				controller.leftPressed();
				break;
			case Input.Keys.RIGHT:
				controller.resetWay();
				controller.rightPressed();
				break;
			case Input.Keys.SPACE:
				controller.upPressed();
				break;
			case Input.Keys.E:
				controller.ePressed();
				break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode){
			case Input.Keys.LEFT:
				controller.resetWay();
				break;
			case Input.Keys.RIGHT:
				controller.resetWay();
				break;
			case Input.Keys.E:
				controller.eReleased();
				break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		switch (findTouch(screenX, screenY)){
			case Input.Keys.LEFT:
				controller.resetWay();
				controller.leftPressed();
				break;
			case Input.Keys.RIGHT:
				controller.resetWay();
				controller.rightPressed();
				break;
			case Input.Keys.SPACE:
				controller.upPressed();
				break;
			case Input.Keys.E:
				controller.ePressed();
				break;
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		switch (findTouch(screenX, screenY)){
			case Input.Keys.LEFT:
				controller.resetWay();
				break;
			case Input.Keys.RIGHT:
				controller.resetWay();
				break;
			case Input.Keys.E:
				controller.eReleased();
				break;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int findTouch(int x, int y){
		int scrY = Gdx.graphics.getHeight();
		int scrX = Gdx.graphics.getWidth();
		float y0 = scrY / 2;
		float x1 = scrX / 100 * 30;
		float x2 = scrX - x1;
		if(y >= y0){
			if (x <= x1){
				return Input.Keys.LEFT;
			}else if(x>=x2){
				return Input.Keys.RIGHT;
			}else {
				return Input.Keys.E;
			}
		}else if (y < y0){
			return Input.Keys.SPACE;
		}
		return -1;
	}

}
