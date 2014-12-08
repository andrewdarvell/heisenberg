package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import ru.darvell.heisenberg.gameobjects.Heisenberg;

/**
 * Created by darvell on 08.12.14.
 */
public class GameWorld {

	private Heisenberg heisenberg;

	public GameWorld(int midPointY){
		heisenberg = new Heisenberg(33, midPointY - 5, 32, 64);
	}

	public void update(float delta){
		heisenberg.update(delta);
	}

	public Heisenberg getHeisenberg(){
		return this.heisenberg;
	}

}
