package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * Created by darvell on 08.12.14.
 */
public class GameRender {

	private GameWorld gameWorld;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	public GameRender(GameWorld gameWorld){
		this.gameWorld = gameWorld;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
	}

	public void render() {
//		Gdx.app.log("GameRenderer", "render");
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.Filled);

		shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

		// Отрисовываем квадрат из gameWorld (Используем ShapeType.Filled)
		shapeRenderer.rect(gameWorld.getRect().x, gameWorld.getRect().y,
				gameWorld.getRect().width, gameWorld.getRect().height);

		// говорим shapeRenderer прекратить отрисовку
		// Мы ДОЛЖНЫ каждый раз это делать
		shapeRenderer.end();

        /*
         * 3. Мы отрисовываем рамку для квадрата
         */

		// Говорим shapeRenderer нарисовать рамку следующей формы
		shapeRenderer.begin(ShapeType.Line);

		// Выбираем цвет RGB Color 255, 109, 120, не прозрачный
		shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

		// Отрисовываем квадрат из gameWorld (Using ShapeType.Line)
		shapeRenderer.rect(gameWorld.getRect().x, gameWorld.getRect().y,
				gameWorld.getRect().width, gameWorld.getRect().height);

		shapeRenderer.end();
	}
}
