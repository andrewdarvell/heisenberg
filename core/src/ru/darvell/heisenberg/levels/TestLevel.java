package ru.darvell.heisenberg.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ru.darvell.heisenberg.HeisenbergGame;
import ru.darvell.heisenberg.gameworld.GameRender;
import ru.darvell.heisenberg.gameworld.GameWorld;

/**
 * Тестовый уровень. Для того, чтобы
 */
public class TestLevel implements Screen {

	final HeisenbergGame heisenbergGame;
	private GameWorld gameWorld;
	private GameRender gameRender;


	public TestLevel(final HeisenbergGame hsg){
		heisenbergGame = hsg;
		gameWorld = new GameWorld();
		gameRender = new GameRender(gameWorld);

	}

	@Override
	public void render(float delta) {
		gameWorld.update(delta);
		gameRender.render();
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
}
