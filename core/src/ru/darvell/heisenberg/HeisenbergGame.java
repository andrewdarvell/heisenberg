package ru.darvell.heisenberg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class HeisenbergGame extends Game {
	SpriteBatch batch;
	Texture img;
	Texture sandBlockImg;
	OrthographicCamera camera;

	Rectangle sandBlock1;

//	Sound dropSound;
//	Music rainMusic;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//Загрузка ресурсов
		//Загрузка изображений
		//img = new Texture("badlogic.jpg");
		sandBlockImg = new Texture(Gdx.files.internal("sand1.png"));
	  	//Настройка камеры
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);

		//Запиливаем простые объекты
		sandBlock1 = new Rectangle();
		sandBlock1.x = 800/2;
		sandBlock1.y = 600/2;
		sandBlock1.width = 32;
		sandBlock1.height = 32;

		// Загрузка звукового эффекта падающей капли и фоновой "музыки" дождя
//		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
//		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		// Сразу же воспроизводиться музыка для фона
//		rainMusic.setLooping(true);
//		rainMusic.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			sandBlock1.x = (int) (touchPos.x - 64 / 2);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) sandBlock1.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) sandBlock1.x += 200 * Gdx.graphics.getDeltaTime();
		if(sandBlock1.x < 0) sandBlock1.x = 0;
		if(sandBlock1.x > 800 - 64) sandBlock1.x = 800 - 64;

		batch.draw(sandBlockImg, sandBlock1.x, sandBlock1.y);
		batch.end();
	}

	@Override
	public void dispose() {
		sandBlockImg.dispose();
		batch.dispose();
	}
}
