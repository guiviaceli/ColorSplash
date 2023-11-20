package Player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class PlayerCamera {
    private OrthographicCamera camera;
    private Vector2 position;

    public PlayerCamera(float viewportWidth, float viewportHeight) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewportWidth, viewportHeight);
        position = new Vector2();
    }

    public void update(Player player) {
        // Assumindo que o player tem métodos getX() e getY() para obter sua posição
        position.set(player.getX(), player.getY());
        camera.position.set(position.x, position.y, 0);
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
