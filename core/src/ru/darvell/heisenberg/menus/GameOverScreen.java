package ru.darvell.heisenberg.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.darvell.heisenberg.HeisenbergGame;
import ru.darvell.heisenberg.levels.TestLevel;

/**
 * Экран гамовера
 */
public class GameOverScreen implements Screen{
	OrthographicCamera camera;
	private SpriteBatch batcher;

	public GameOverScreen(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		batcher = new SpriteBatch();

		batcher.setProjectionMatrix(camera.combined);
		batcher.begin();
		System.out.println("Game over");
		batcher.end();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();


		if (Gdx.input.isTouched()) {
			HeisenbergGame.setMainScreen();
		}
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
