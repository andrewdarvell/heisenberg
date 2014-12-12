package ru.darvell.heisenberg.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Обычнаяя платформа. На ней просто стоят.
 */
public class Platform {
    private Vector2 position;

    private final int width = 32;
    private final int height = 32;

    public Platform(int startX, int startY){
        position = new Vector2();
        position.x = startX;
        position.y = startY;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
