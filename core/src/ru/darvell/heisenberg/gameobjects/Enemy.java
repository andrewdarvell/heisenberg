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
public class  Enemy {

	public static final int LIFES_COUNT = 5;
	public static final float SPEED = 5f;
	public static final int RELOAD_TIME = 100;
	public static final int TIME_TO_GO = 150;

	private int reloading = 0;
	private boolean border = false;
	private int face = -1;
	private int steps = 0;
	private int directToGo = 1;

	Body enemyBody;
	public Fixture enemyFixture;


	private int lifes;
	private boolean isAlive;
	//Нужно стрелять или нет
	private boolean needBullet = false;
	//Состояние погони за игроком
	private boolean inRun = false;



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
		steps = 0;


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

	public boolean getNeedBullet(){
		if (needBullet){
			needBullet = false;
			reloading = RELOAD_TIME;
			return true;
		}else {
			return false;
		}
	}

	public void update(float delta, Vector2 playerPosition){
		if (isAlive) {
			if (reloading > 0) {
				reloading--;
			}
			Vector2 pos = enemyBody.getPosition();

//			if (playerPosition.x <= pos.x) {
//				face = -1;
//			} else {
//				face = 1;
//			}
//			double distancePlayer = Math.abs(Math.pow(playerPosition.x - pos.x, 2) + Math.pow(playerPosition.y - pos.y, 2));
//			Vector2 vel = new Vector2();
//			vel.y = enemyBody.getLinearVelocity().y;
//			vel.x = enemyBody.getLinearVelocity().x;
			//Движение
//			if (distancePlayer < 3000 && distancePlayer > 20) {
//				vel.x = SPEED * face;
//			} else {
//				vel.x = 0;
//			}
			//Стрельба
//			if (distancePlayer > 20 && distancePlayer < 300) {
//				if (reloading == 0) {
//					needBullet = true;
//				}
//			}
			if (steps < TIME_TO_GO){
				steps += 1;
			}else{
				steps = 0;
				directToGo *= -1;
			}
			Vector2 vel = enemyBody.getLinearVelocity();
			vel.x = SPEED * directToGo;

			enemyBody.setLinearVelocity(vel);
			steps += 1;
		}
	}

	public int getFace() {
		return face;
	}

	public float getX(){
		return enemyBody.getPosition().x;
	}

	public float getY(){
		return enemyBody.getPosition().y;
	}
}
