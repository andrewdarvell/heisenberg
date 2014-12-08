package ru.darvell.heisenberg.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ru.darvell.heisenberg.HeisenbergGame;
import ru.darvell.heisenberg.gameworld.GameRender;
import ru.darvell.heisenberg.gameworld.GameWorld;
import ru.darvell.heisenberg.helpers.InputHandler;

/**
 * Тестовый уровень. Для того, чтобы
 */
public class TestLevel implements Screen {

	final HeisenbergGame heisenbergGame;
	private GameWorld gameWorld;
	private GameRender gameRender;
	private float runTime = 0;


	public TestLevel(final HeisenbergGame hsg){
		heisenbergGame = hsg;
		gameWorld = new GameWorld(Gdx.graphics.getHeight());
		gameRender = new GameRender(gameWorld);
		Gdx.input.setInputProcessor(new InputHandler(gameWorld.getHeisenberg()));

	}

	@Override
	public void render(float delta) {
		runTime += delta;
		gameWorld.update(delta);
		gameRender.render(runTime);
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
