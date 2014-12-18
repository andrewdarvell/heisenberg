package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import ru.darvell.heisenberg.gameworld.GameWorld;

import java.util.Objects;


/**
 * Сферический противник в вакууме
 */
public class Enemy {

	Body enemyBody;
	public Fixture enemyFixture;

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

		String s = "enemy1";
		enemyBody.setUserData(s);

	}

	public Body getBody(){
		return enemyBody;
	}

	public Vector2 getPosition(){
		return enemyBody.getPosition();
	}
}
