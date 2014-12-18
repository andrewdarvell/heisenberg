package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import ru.darvell.heisenberg.gameobjects.Bullet;
import ru.darvell.heisenberg.gameobjects.Enemy;

/**
 * Класс для отслеживания коллизий
 */
public class GameContactFilter implements ContactFilter{


	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		Filter filterA = fixtureA.getFilterData();
		Filter filterB = fixtureB.getFilterData();
		if((filterA.categoryBits == GameWorld.CATEGORY_HEISENBERG && filterB.categoryBits == GameWorld.CATEGORY_BLOCK) ||
				(filterB.categoryBits == GameWorld.CATEGORY_HEISENBERG && filterA.categoryBits == GameWorld.CATEGORY_BLOCK )) {
//			System.out.println("11");

			return true;
		}
		if((filterA.categoryBits == GameWorld.CATEGORY_BULLET && filterB.categoryBits == GameWorld.CATEGORY_BLOCK) ||
				(filterB.categoryBits == GameWorld.CATEGORY_BULLET && filterA.categoryBits == GameWorld.CATEGORY_BLOCK )) {

			return true;
		}

		if((filterA.categoryBits == GameWorld.CATEGORY_BLOCK && filterB.categoryBits == GameWorld.CATEGORY_ENEMY) ||
				(filterB.categoryBits == GameWorld.CATEGORY_BLOCK && filterA.categoryBits == GameWorld.CATEGORY_ENEMY )) {

			return true;
		}

		if((filterA.categoryBits == GameWorld.CATEGORY_ENEMY && filterB.categoryBits == GameWorld.CATEGORY_BULLET) ||
				(filterB.categoryBits == GameWorld.CATEGORY_ENEMY && filterA.categoryBits == GameWorld.CATEGORY_BULLET )) {

			if (filterA.categoryBits == GameWorld.CATEGORY_BULLET){
				Bullet bullet = (Bullet)fixtureA.getUserData();
				Enemy enemy = (Enemy)fixtureB.getUserData();
				bullet.setStatus(false);
				enemy.hit();

			}
			if (filterB.categoryBits == GameWorld.CATEGORY_BULLET){
				Bullet bullet = (Bullet)fixtureB.getUserData();
				Enemy enemy = (Enemy)fixtureA.getUserData();
				bullet.setStatus(false);
				enemy.hit();
			}

			return true;
		}

		return false;
	}
}
