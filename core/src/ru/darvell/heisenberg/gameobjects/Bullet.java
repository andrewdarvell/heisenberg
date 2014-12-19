package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Класс реализует снаряд (пулю)
 */
public class Bullet {

	private boolean isAllive;
	public static float SPEED = 500f;

	Body bulletBody;
//	public Fixture bulletFixture;
	Vector2 velosity = new Vector2();

	public Bullet(Body body, int direction){
		bulletBody = body;
		bulletBody.setBullet(true);
		isAllive = true;
		bulletBody.applyLinearImpulse(SPEED * direction, 0, bulletBody.getPosition().x, bulletBody.getPosition().y, true);
	}

	public boolean getStatus(){
		return isAllive;
	}

	public void setStatus(boolean b){
		isAllive = false;
	}

	public Body getBody(){
		return bulletBody;
	}

	public void update(){
//		bulletBody.setLinearVelocity(velosity);
	}

}
