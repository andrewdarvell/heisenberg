package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Класс реализует снаряд (пулю)
 */
public class Bullet {

	private boolean isAllive;
	public static float SPEED = 20f;

	Body bulletBody;
	public Fixture bulletFixture;
	Vector2 velosity = new Vector2();

	public Bullet(Body body, int direction){
		bulletBody = body;
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(0.2f, 0.2f);
		bulletFixture = bulletBody.createFixture(poly, 3);
		bulletBody.setBullet(true);
		isAllive = true;
		velosity.x = SPEED * direction;

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
		bulletBody.setLinearVelocity(velosity);
	}

}
