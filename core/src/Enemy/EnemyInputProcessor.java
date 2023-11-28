package Enemy;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
public class EnemyInputProcessor implements InputProcessor{
    public boolean ATTACK1 = false;
    boolean UP1,DOWN1, LEFT1, RIGHT1;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {

            case Keys.I:
                UP1 = true;
                System.out.println("Key Down: " + keycode);

                break;
            case Keys.K:
                DOWN1 = true;
                System.out.println("Key Down: " + keycode);

                break;
            case Keys.J:
                LEFT1 = true;
                System.out.println("Key Down: " + keycode);

                break;
            case Keys.L:
                RIGHT1 = true;
                System.out.println("Key Down: " + keycode);

                break;
            case Keys.CONTROL_RIGHT:
                System.out.println("Key Down: " + keycode);

                ATTACK1 = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.I:
                UP1 = false;
                break;
            case Keys.K:
                DOWN1 = false;
                break;
            case Keys.J:
                LEFT1 = false;
                break;
            case Keys.L:
                RIGHT1 = false;
                break;
            case Keys.CONTROL_RIGHT:
                ATTACK1 = false;
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
