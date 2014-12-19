package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import ru.darvell.heisenberg.gameworld.GameWorld;

/**
 * Пули игрока
 */
public class PlayerBullet extends Bullet{

	public Fixture bulletFixture;

	public PlayerBullet(Body body, int direction) {
		super(body, direction);

		PolygonShape poly = new PolygonShape();
		poly.setAsBox(0.2f, 0.2f);
		bulletFixture = bulletBody.createFixture(poly, 3);
		poly.dispose();

		Filter f = new Filter();
		f.categoryBits = GameWorld.CATEGORY_BULLET_PLAYER;
		f.maskBits = GameWorld.MASK_BULLET_PLAYER;
		bulletFixture.setFilterData(f);
		bulletFixture.setUserData(this);

	}
}
