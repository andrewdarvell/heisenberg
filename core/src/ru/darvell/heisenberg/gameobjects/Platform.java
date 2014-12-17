package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import ru.darvell.heisenberg.gameworld.GameWorld;

/**
 * Обычнаяя платформа. На ней просто стоят.
 */

//TODO Детальнее проработать физику
//TODO документировать

public class Platform {
    private Vector2 position;

    private Body platBody;
    public Fixture platFixture;

    public static final float WIDTH = 3.2f;
    public static final float HEIGHT = 3.2f;

    public static final float REAL_WIDTH = 32f;
    public static final float REAL_HEIGHT = 32f;


    public Platform(Body platBody){
//        position = new Vector2();
        this.platBody = platBody;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(1.6f, 1.6f);
        platFixture = platBody.createFixture(poly, 3);
        platBody.setBullet(false);

        Filter f = new Filter();
        f.categoryBits = GameWorld.CATEGORY_BLOCK;
        f.maskBits = GameWorld.MASK_BLOCK;
        platFixture.setFilterData(f);
        platFixture.setFilterData(f);

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
}
