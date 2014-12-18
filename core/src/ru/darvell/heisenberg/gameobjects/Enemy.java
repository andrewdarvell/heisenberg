package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import ru.darvell.heisenberg.gameworld.GameWorld;


/**
 * Сферический противник в вакууме
 */
public class Enemy {

	public static final int LIFES_COUNT = 5;

	Body enemyBody;
	public Fixture enemyFixture;

	private int lifes;
	private boolean isAlive;



	public Enemy(Body body){

		enemyBody = body;

		PolygonShape poly = new PolygonShape();
		poly.setAsBox(0.5f, 2f);
		enemyFixture = enemyBody.createFixture(poly, 1);
		poly.dispose();

		Filter filter = new Filter();
		filter.categoryBits = GameWorld.CATEGORY_ENEMY;
		filter.maskBits = GameWorld.MASK_ENEMY;

		enemyFixture.setFilterData(filter);


		enemyBody.setBullet(true);
		enemyFixture.setUserData(this);

		lifes = LIFES_COUNT;
		isAlive = true;


	}

	public Body getBody(){
		return enemyBody;
	}

	public Vector2 getPosition(){
		return enemyBody.getPosition();
	}

	public void hit(){
		lifes -= 1;
		if (lifes == 0){
			isAlive = false;
		}
	}

	public boolean getStatus(){
		return isAlive;
	}
}
