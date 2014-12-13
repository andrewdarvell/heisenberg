package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by darvell on 08.12.14.
 * http://habrahabr.ru/post/243471/#Day3
 */
public class Heisenberg {

//	private Vector2 velocity;

	private boolean moveLeft = false;
	private boolean moveRight = false;
	private boolean isJump = false;

	private final int width = 32;
	private final int height = 64;

	private Body heisBody;
	public Fixture playerPhysicsFixture;
	public Fixture playerSensorFixture;

	//TODO проработать размер Body для персонажа
	//TODO документировать
	public Heisenberg(Body heisBody) {
//		position = new Vector2(x, y);


		this.heisBody = heisBody;
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(32f, 32f);
		playerPhysicsFixture = heisBody.createFixture(poly, 0);
		poly.dispose();

		CircleShape circle = new CircleShape();
		circle.setRadius(32f);
		circle.setPosition(new Vector2(0, -10f));
		playerSensorFixture = heisBody.createFixture(circle, 0);
		circle.dispose();

		heisBody.setBullet(true);
		setFriction(100F);

	}

	Vector2 velocity = new Vector2();
	public void update(float delta) {
//		if (moveRight){
//			position.x += 100 * Gdx.graphics.getDeltaTime();
//		}
//		if (moveLeft){
//			position.x -= 100 * Gdx.graphics.getDeltaTime();
////		}
		Vector2 vel = heisBody.getLinearVelocity();
		velocity.y = vel.y;
		System.out.println(heisBody.getLinearVelocity());
		heisBody.setLinearVelocity(velocity);
		if (isJump){
			heisBody.applyLinearImpulse(0f, 8f, heisBody.getPosition().x, heisBody.getPosition().y, true);
			isJump = false;
		}
	}

	public void jump(){
		isJump = true;
	}

	public void resetVelocity(){
		getVelocity().x = 0;
		getVelocity().y = 0;
	}

//	public void onClick() {
//		velocity.y = -140;
//	}

	public float getX(){
		return  heisBody.getPosition().x;
	}

	public float getY(){
		return  heisBody.getPosition().y;
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
	}
}
