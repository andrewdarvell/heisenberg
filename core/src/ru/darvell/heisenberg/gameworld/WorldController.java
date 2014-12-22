package ru.darvell.heisenberg.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.utils.Array;
import ru.darvell.heisenberg.gameobjects.Heisenberg;

import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер мира. Обрабатывает всё что происходит
 */
public class WorldController {
    GameWorld gameWorld;
    public static boolean grounded;

    enum Keys {
        LEFT, RIGHT, UP, DOWN, E
    }



    static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
        keys.put(Keys.E, false);
    }

    public WorldController(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    public void update(float delta) {
        grounded = isPlayerGrounded(Gdx.graphics.getDeltaTime());
//        System.out.println();

        processInput();
        gameWorld.delDeadBullets();
//        gameWorld.updateBullets();
        gameWorld.getHeisenberg().update(delta);

        gameWorld.updateEnemies(delta);
    }

    //флаг устанавливаем, что движемся влево
    public void leftPressed() {

        keys.get(keys.put(Keys.LEFT, true));
    }

    //флаг устанавливаем, что движемся вправо
    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    //освобождаем флаги
    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }
    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }
    //флаг устанавливаем, что движемся вверх
    public void upPressed() {
        keys.get(keys.put(Keys.UP, true));
    }

    public void upReleased() {
        keys.get(keys.put(Keys.UP, false));
    }

    public void ePressed(){
        keys.get(keys.put(Keys.E, true));
    }

    public void eReleased(){
        keys.get(keys.put(Keys.E, false));
    }

    public void resetWay(){
        rightReleased();
        leftReleased();
        //downReleased();
        upReleased();
        gameWorld.getHeisenberg().resetVelocity();
    }

    private boolean isPlayerGrounded(float deltaTime) {
        Array<Contact> contactList = gameWorld.get2dBWorld().getContactList();
        for(int i = 0; i < contactList.size; i++) {
            Contact contact = contactList.get(i);
            if(contact.isTouching() && (contact.getFixtureA() == gameWorld.getHeisenberg().playerSensorFixture ||
                    contact.getFixtureB() == gameWorld.getHeisenberg().playerSensorFixture)) {

                Vector2 pos = gameWorld.getHeisenberg().getPosition();
                WorldManifold manifold = contact.getWorldManifold();
                boolean below = true;
                for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                    below &= (manifold.getPoints()[j].y < pos.y - 1.5f);
                }

                if(below) {
//					if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("p")) {
//						groundedPlatform = (MovingPlatform)contact.getFixtureA().getBody().getUserData();
//					}
//
//					if(contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("p")) {
//						groundedPlatform = (MovingPlatform)contact.getFixtureB().getBody().getUserData();
//					}
                    return true;
                }

                return false;
            }
        }
        return false;
    }

    private void processInput() {

        Heisenberg player = gameWorld.getHeisenberg();
        if (keys.get(Keys.LEFT)) {
            player.getVelocity().x = -Heisenberg.SPEED;
            player.setDirection(-1);
        }

        if (keys.get(Keys.RIGHT)){
            player.getVelocity().x = Heisenberg.SPEED;
            player.setDirection(1);
        }

        if(!grounded)
            player.setFriction(0F);
        else{
            if (!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT))
                player.setFriction(200F);
            else
                player.setFriction(0.2F);
        }

        if (keys.get(Keys.UP))
            if(grounded) {
                player.jump();
                this.upReleased();
                //player.getBody().setGravityScale(-3);
                //world.getWorld().setGravity(new Vector2(0, 20));
            }

        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
                (!keys.get(Keys.LEFT) && (!keys.get(Keys.RIGHT)))) {

            player.getVelocity().x = 0;
        }

        if (keys.get(Keys.E)){
            gameWorld.createBulletPlayer();
            eReleased();
        }

    }
}
