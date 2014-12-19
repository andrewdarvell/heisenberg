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

		// Тело игрока и блок
		if((filterA.categoryBits == GameWorld.CATEGORY_HEISENBERG && filterB.categoryBits == GameWorld.CATEGORY_BLOCK) ||
				(filterB.categoryBits == GameWorld.CATEGORY_HEISENBERG && filterA.categoryBits == GameWorld.CATEGORY_BLOCK )) {
			return true;
		}

		// Сенсор игрока и блок
		if((filterA.categoryBits == GameWorld.CATEGORY_HEIS_LEGS && filterB.categoryBits == GameWorld.CATEGORY_BLOCK) ||
				(filterB.categoryBits == GameWorld.CATEGORY_HEIS_LEGS && filterA.categoryBits == GameWorld.CATEGORY_BLOCK )) {
			return true;
		}

		// Пуля игрока и блок
		if((filterA.categoryBits == GameWorld.CATEGORY_BULLET_PLAYER && filterB.categoryBits == GameWorld.CATEGORY_BLOCK) ||
				(filterB.categoryBits == GameWorld.CATEGORY_BULLET_PLAYER && filterA.categoryBits == GameWorld.CATEGORY_BLOCK )) {

			return true;
		}

		//Блок и враг
		if((filterA.categoryBits == GameWorld.CATEGORY_BLOCK && filterB.categoryBits == GameWorld.CATEGORY_ENEMY) ||
				(filterB.categoryBits == GameWorld.CATEGORY_BLOCK && filterA.categoryBits == GameWorld.CATEGORY_ENEMY )) {

			return true;
		}

		// Враг и пуля игрока
		if((filterA.categoryBits == GameWorld.CATEGORY_ENEMY && filterB.categoryBits == GameWorld.CATEGORY_BULLET_PLAYER) ||
				(filterB.categoryBits == GameWorld.CATEGORY_ENEMY && filterA.categoryBits == GameWorld.CATEGORY_BULLET_PLAYER)) {

			if (filterA.categoryBits == GameWorld.CATEGORY_BULLET_PLAYER){
				Bullet bullet = (Bullet)fixtureA.getUserData();
				Enemy enemy = (Enemy)fixtureB.getUserData();
				bullet.setStatus(false);
				enemy.hit();

			}
			if (filterB.categoryBits == GameWorld.CATEGORY_BULLET_PLAYER){
				Bullet bullet = (Bullet)fixtureB.getUserData();
				Enemy enemy = (Enemy)fixtureA.getUserData();
				bullet.setStatus(false);
				enemy.hit();
			}

			return true;
		}

		//Игрок и пуля врага
		if((filterA.categoryBits == GameWorld.CATEGORY_HEISENBERG && filterB.categoryBits == GameWorld.CATEGORY_BULLET_ENEMY) ||
				(filterB.categoryBits == GameWorld.CATEGORY_HEISENBERG && filterA.categoryBits == GameWorld.CATEGORY_BULLET_ENEMY)) {

			if (filterA.categoryBits == GameWorld.CATEGORY_BULLET_ENEMY){
				Bullet bullet = (Bullet)fixtureA.getUserData();
				bullet.setStatus(false);
//				enemy.hit();

			}
			if (filterB.categoryBits == GameWorld.CATEGORY_BULLET_ENEMY){
				Bullet bullet = (Bullet)fixtureB.getUserData();
				bullet.setStatus(false);
//				enemy.hit();
			}
			System.out.println("Player hit\'s");
			return true;
		}

		return false;
	}
}
