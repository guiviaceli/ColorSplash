package Enemy;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
public class EnemyInputProcessor implements InputProcessor{
    public boolean ATTACK = false;
    boolean UP,DOWN, LEFT, RIGHT;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.UP:
                UP = true;
                break;
            case Keys.DOWN:
                DOWN = true;
                break;
            case Keys.LEFT:
                LEFT = true;
                break;
            case Keys.RIGHT:
                RIGHT = true;
                break;
            case Keys.CONTROL_RIGHT:
                ATTACK = true;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.UP:
                UP = false;
                break;
            case Keys.DOWN:
                DOWN = false;
                break;
            case Keys.LEFT:
                LEFT = false;
                break;
            case Keys.RIGHT:
                RIGHT = false;
                break;
            case Keys.CONTROL_RIGHT:
                ATTACK = false;
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
