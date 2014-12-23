package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.darvell.heisenberg.gameworld.GameWorld;

/**
 * Created by darvell on 08.12.14.
 * http://habrahabr.ru/post/243471/#Day3
 */
public class Heisenberg {

	private boolean moveLeft = false;
	private boolean moveRight = false;
	private boolean isJump = false;
	private boolean isGrounded = false;
	private int direction = 1;
	private int lifes;
	private boolean isAlive = true;

	public final static float SPEED = 20f;
	public final static float REAL_WIDTH = 32;
	public final static float REAL_HEIGHT = 32;
	private final int MAX_LIFE_COUNT = 20;

	private final int width = 32;
	private final int height = 64;

	private Body heisBody;
	public Fixture playerPhysicsFixture;
	public Fixture playerSensorFixture;

	Vector2 velocity = new Vector2();

	//TODO подумать стоит ли делать руку персонажа с физикой
	//TODO документировать
	public Heisenberg(Body heisBody) {

		this.heisBody = heisBody;
		PolygonShape poly = new PolygonShape();
//		poly.setAsBox(5f, 20f);
		poly.setAsBox(0.5f, 2f);
		playerPhysicsFixture = heisBody.createFixture(poly, 0);
		poly.dispose();

		CircleShape circle = new CircleShape();
		circle.setRadius(0.5f);
		circle.setPosition(new Vector2(0, -1.9f));

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.0f;
		fixtureDef.friction = 0.0f;
//		fixtureDef.restitution = 20.0f;//  bouncy

		playerSensorFixture = heisBody.createFixture(fixtureDef);
		circle.dispose();

		Filter f = new Filter();
		f.categoryBits = GameWorld.CATEGORY_HEISENBERG;
		f.maskBits = GameWorld.MASK_HEISENBERG;

		playerPhysicsFixture.setFilterData(f);

		f = new Filter();
		f.categoryBits = GameWorld.CATEGORY_HEIS_LEGS;
		f.maskBits = GameWorld.MASK_HEISLEGS;
		playerSensorFixture.setFilterData(f);


		heisBody.setBullet(true);
		playerPhysicsFixture.setUserData(this);

		lifes = MAX_LIFE_COUNT;
		isAlive = true;


	}


	public void update(float delta) {
		Vector2 vel = heisBody.getLinearVelocity();
		velocity.y = vel.y;
		heisBody.setLinearVelocity(velocity);
		if(isJump) {
			heisBody.applyLinearImpulse(0, 50f, heisBody.getPosition().x,  heisBody.getPosition().y, true);
			isJump = false;
		}
//		System.out.println(heisBody.getPosition());
	}

	public void jump(){
		isJump = true;
	}

	public void resetVelocity(){
		getVelocity().x = 0;
		getVelocity().y = 0;
	}

	public Vector2 getPosition(){
		return heisBody.getPosition();
	}

	public Vector2 getVelocity(){
		return velocity;
	}

	public void setVelocity(Vector2 velocity){
		this.velocity = velocity;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public void moveLeft(){
		moveRight = false;
		moveLeft = true;
	}

	public void moveRight(){
		moveLeft = false;
		moveRight = true;
	}

	public void stopMoveRight(){
		moveRight = false;
	}

	public void stopMoveLeft(){
		moveLeft = false;
	}

	public Body getBody(){
		return heisBody;
	}

	//Установка силы трения
	public void setFriction(float f){
		playerPhysicsFixture.setFriction(f);
		playerSensorFixture.setFriction(f);
	}

	public void setGrounded(boolean bool){
		isGrounded = bool;
	}

	public boolean isGrounded(){
		return  isGrounded;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getX(){
		return heisBody.getPosition().x;
	}

	public float getY(){
		return heisBody.getPosition().y;
	}

	public void hit(int power){
		lifes -= power;
		if (lifes <= 0){
			isAlive = false;
			System.out.println("Player killed");
		}
	}

	public boolean isAlive(){
		return isAlive;
	}
}
