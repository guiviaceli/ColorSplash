package Player;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class PlayerInputProcessor implements InputProcessor {
    public boolean ATTACK = false;
    boolean UP, DOWN, LEFT, RIGHT;
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.W:
                UP = true;
                break;
            case Keys.S:
                DOWN = true;
            case Keys.A:
                LEFT = true;
                break;
            case Keys.D:
                RIGHT = true;
                break;
            case Keys.SPACE:
                ATTACK = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.W:
                UP = false;
                break;
            case Keys.S:
                DOWN = false;
            case Keys.A:
                LEFT = false;
                break;
            case Keys.D:
                RIGHT = false;
                break;
            case Keys.SPACE:
                ATTACK = false;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
