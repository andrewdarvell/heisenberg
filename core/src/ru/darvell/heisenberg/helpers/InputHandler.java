package ru.darvell.heisenberg.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import ru.darvell.heisenberg.gameobjects.Heisenberg;

/**
 * Created by darvell on 08.12.14.
 */
public class InputHandler implements InputProcessor {
    private Heisenberg heisenberg;

    public InputHandler(Heisenberg heisenberg){
        this.heisenberg = heisenberg;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
                heisenberg.moveLeft();
                break;
            case Input.Keys.RIGHT:
                heisenberg.moveRight();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if ((keycode == Input.Keys.LEFT) || (keycode == Input.Keys.RIGHT)){
            heisenberg.stopMove();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        System.out.println(screenX+";"+screenY);
        int y = Gdx.graphics.getHeight();
        int x = Gdx.graphics.getWidth();
        float y0 = y/2;
        float x1 = x/100 * 30;
        float x2 = x-x1;
//        System.out.println(x+";"+y);
//        System.out.println(y0+";"+x1+";"+x2);
        if(screenY >= y0){
            if (screenX <= x1){
                heisenberg.moveLeft();
                System.out.println("Left!!");
            }else if(screenX>=x2){
                heisenberg.moveRight();
                System.out.println("Right!!");
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        heisenberg.stopMove();
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
}
