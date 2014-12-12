package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by darvell on 08.12.14.
 * http://habrahabr.ru/post/243471/#Day3
 */
public class Heisenberg {

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private boolean moveLeft = false;
	private boolean moveRight = false;

	private float rotation; // Для обработки поворота птицы
	private int width;
	private int height;

	public Heisenberg(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
	}

	public void update(float delta) {
		if (moveRight){
			position.x += 100 * Gdx.graphics.getDeltaTime();
		}
		if (moveLeft){
			position.x -= 100 * Gdx.graphics.getDeltaTime();
		}

	}

	public void onClick() {
		velocity.y = -140;
	}

	public float getX(){
		return  position.x;
	}

	public float getY(){
		return  position.y;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public void moveLeft(){
		moveRight = false;
		moveLeft = true;
	}

	public void moveRight(){
		moveLeft = false;
		moveRight = true;
	}

	public void stopMoveRight(){
		moveRight = false;
	}

	public void stopMoveLeft(){
		moveLeft = false;
	}

}
