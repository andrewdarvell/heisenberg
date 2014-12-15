package ru.darvell.heisenberg.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import ru.darvell.heisenberg.gameobjects.Heisenberg;

/**
 * Обработчик нажатий кнопок и экрана
 */
//TODO документировать

public class InputHandler implements InputProcessor {
    private Heisenberg heisenberg;

    private final int LEFT = 1;
    private final int RIGHT = 2;
    private final int UP = 3;
    private final int DOWN = 4;

    public InputHandler(Heisenberg heisenberg){
        this.heisenberg = heisenberg;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
//                heisenberg.moveLeft();
//                System.out.println(heisenberg.getVelocity().x);
                heisenberg.setFriction(0F);
                heisenberg.getVelocity().x = -5f;
                break;
            case Input.Keys.RIGHT:
//                System.out.println(heisenberg.getVelocity().x);
                heisenberg.setFriction(0F);
                heisenberg.getVelocity().x = 5f;
                break;
            case Input.Keys.SPACE:
                heisenberg.setFriction(0F);
                heisenberg.jump();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
//                heisenberg.stopMoveLeft();
                heisenberg.resetVelocity();
                heisenberg.setFriction(10F);
                break;
            case Input.Keys.RIGHT:
//                heisenberg.stopMoveRight();
                heisenberg.setFriction(10F);
                heisenberg.resetVelocity();
                break;

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (findTouch(screenX, screenY)){
            case LEFT:
                heisenberg.moveLeft();
                break;
            case RIGHT:
                heisenberg.moveRight();
                break;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        switch (findTouch(screenX, screenY)){
            case LEFT:
                heisenberg.stopMoveLeft();
                break;
            case RIGHT:
                heisenberg.stopMoveRight();
                break;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int findTouch(int x, int y){
        int scrY = Gdx.graphics.getHeight();
        int scrX = Gdx.graphics.getWidth();
        float y0 = scrY / 2;
        float x1 = scrX / 100 * 30;
        float x2 = scrX - x1;
        if(y >= y0){
            if (x <= x1){
                return LEFT;
            }else if(x>=x2){
                return RIGHT;
            }
        }
        return -1;
    }

}
