package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Класс для отслеживания коллизий
 */
public class GameContactFilter implements ContactFilter{

	public String contact = "";

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		Filter filterA = fixtureA.getFilterData();
		Filter filterB = fixtureB.getFilterData();
		if((filterA.categoryBits == GameWorld.CATEGORY_HEISENBERG && filterB.categoryBits == GameWorld.CATEGORY_BLOCK) ||
				(filterB.categoryBits == GameWorld.CATEGORY_BLOCK && filterA.categoryBits == GameWorld.CATEGORY_HEISENBERG )) {
//			System.out.println("11");
			return true;
		}
//		System.out.println("22");
		return true;
	}
}
