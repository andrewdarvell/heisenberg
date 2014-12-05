package ru.darvell.heisenberg.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ru.darvell.heisenberg.HeisenbergGame;

/**
 * Главное меню игры
 */
public class MainMenuScreen implements Screen{

	final HeisenbergGame heisenbergGame;
	OrthographicCamera camera;

	public MainMenuScreen(final HeisenbergGame hsg){
		heisenbergGame = hsg;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		heisenbergGame.getBatch().setProjectionMatrix(camera.combined);

		heisenbergGame.getBatch().begin();
		heisenbergGame.getFont().draw(heisenbergGame.getBatch(), "This is Heisenberg Revenge!!! ", 100, 150);
		heisenbergGame.getFont().draw(heisenbergGame.getBatch(), "To rule them All ", 100, 100);
		heisenbergGame.getFont().draw(heisenbergGame.getBatch(), "Tap anywhere to begin!", 100, 50);
		heisenbergGame.getBatch().end();

		if (Gdx.input.isTouched()) {
			//heisenbergGame.setScreen(new GameScreen(game));
			dispose();
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
