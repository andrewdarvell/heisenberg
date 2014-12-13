package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Обычнаяя платформа. На ней просто стоят.
 */

//TODO Детальнее проработать физику

public class Platform {
    private Vector2 position;

    private Body platBody;
    public Fixture platFixture;

    private final int width = 32;
    private final int height = 32;

    public Platform(Body platBody){
//        position = new Vector2();
        this.platBody = platBody;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(32f, 32f);
        platFixture = platBody.createFixture(poly, 3);
        platBody.setBullet(false);

//        position.x = startX;
//        position.y = startY;
    }

    public float getX(){
        return platBody.getPosition().x;
    }

    public float getY(){
        return platBody.getPosition().y;
    }

    public Vector2 getPosition(){
        return platBody.getPosition();
    }

    public Body getBody(){
        return platBody;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
