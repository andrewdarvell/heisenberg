package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import ru.darvell.heisenberg.gameworld.GameWorld;

/**
 * Класс реализует снаряд (пулю)
 */
public class Bullet {

	private boolean isAllive;
	public static float SPEED = 500f;

	private static int idLast = 0;

	private int id;


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

		Filter f = new Filter();
		f.categoryBits = GameWorld.CATEGORY_BULLET;
		f.maskBits = GameWorld.MASK_BULLET;
		bulletFixture.setFilterData(f);

//		velosity.x = SPEED * direction;
		bulletBody.applyLinearImpulse(SPEED * direction, 0, bulletBody.getPosition().x, bulletBody.getPosition().y, true);
		bulletFixture.setUserData(this);
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
