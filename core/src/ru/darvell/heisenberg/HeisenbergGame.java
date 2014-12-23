package ru.darvell.heisenberg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.darvell.heisenberg.helpers.AssetLoader;
import ru.darvell.heisenberg.menus.GameOverScreen;
import ru.darvell.heisenberg.menus.MainMenuScreen;


public class HeisenbergGame extends Game {
	static HeisenbergGame game;

	SpriteBatch batch;
	BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		// libGDX по умолчанию использует Arial шрифт.
		font = new BitmapFont();
		AssetLoader.load();
		game = this;
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // важно!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		AssetLoader.dispose();
	}

	public SpriteBatch getBatch(){
		return this.batch;
	}

	public BitmapFont getFont(){
		return this.font;
	}

	public static void setMainScreen(){
		game.setScreen(new MainMenuScreen(game));
	}

	public static void setGameOverScreen(){
		game.setScreen(new GameOverScreen());
	}

}
