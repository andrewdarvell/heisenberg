package ru.darvell.heisenberg.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.darvell.heisenberg.HeisenbergGame;
import ru.darvell.heisenberg.gameworld.GameRender;
import ru.darvell.heisenberg.gameworld.GameWorld;
import ru.darvell.heisenberg.gameworld.WorldController;

/**
 * Тестовый уровень. Для того, чтобы
 */

// Прыжок в телефоне
//TODO Заново если персонаж умирает
//TODO Звуки
//TODO Игровые объекты (жизнь, мет)
//TODO Стоячий на месте враг (Чувак в коляске)
// Поменять цвет фона
//TODO Главный экран игры и экран выбора уровней
//TODO Несложный босс (Несложный в плане реализации)
//TODO Подумать над разного рода усилениями
//TODO Повесить рекламу
//Подгрузка врагов с карты
//TODO Отображение инф о игроке
//TODO Система очков (Продумать)
//TODO Подгрузка разных уровней
//TODO Разобраться с масштабирование в рендере



//TODO документировать
public class TestLevel implements Screen, InputProcessor {

	final HeisenbergGame heisenbergGame;
	private GameWorld gameWorld;
	private GameRender gameRender;
	private WorldController controller;

	public int width;
	public int height;

	private TiledMap tiledMap;
	FPSLogger logger = new FPSLogger();



	public TestLevel(final HeisenbergGame hsg){
		tiledMap = new TmxMapLoader().load("data/maps/testmap.tmx");
//		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);


		heisenbergGame = hsg;

		gameWorld = new GameWorld(Gdx.graphics.getHeight(), tiledMap);

		gameRender = new GameRender(gameWorld, tiledMap);
		controller = new WorldController(gameWorld);
		Gdx.input.setInputProcessor(this);
//		GLProfiler.enable();

	}

	@Override
	public void render(float delta) {


		controller.update(delta);
		gameRender.render(delta);

		logger.log();
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
			case Input.Keys.U:
				controller.leftPressed();
				controller.upPressed();
				break;
			case Input.Keys.I:
				controller.rightPressed();
				controller.upPressed();
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
			case Input.Keys.U:
				controller.resetWay();
				break;
			case Input.Keys.I:
				controller.resetWay();
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
			if (x <= x1){
				return Input.Keys.U;
			}else if(x>=x2){
				return Input.Keys.I;
			}else {
				return Input.Keys.SPACE;
			}
		}
		return -1;
	}

}
