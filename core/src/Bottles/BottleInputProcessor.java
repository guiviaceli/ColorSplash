//package Bottles;
//
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.graphics.Camera;
//import Player.Player; // Importe sua classe Player
//
//
//public class BottleInputProcessor implements InputProcessor {
//    private Player player; // Referência ao objeto Player
//
//    private Camera camera; // Assuming you have a camera for coordinate conversion
//    public BottleInputProcessor(Camera camera, Player player) {
//        this.camera = camera;
//        this.player = player;
//    }
//    @Override
//    public boolean keyDown(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        if (button == Input.Buttons.LEFT) {
//            // Convert screen coordinates to world coordinates
//            Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
//            camera.unproject(worldCoordinates); // Converts in-place
//
//            // Determine the bottle type to throw, this is just an example
//            Bottle.Type type = Bottle.Type.BLACK; // You can add logic to select the type
//
//            Vector2 startPosition = player.getPosition();
//            // Call throwBottle with the player's position and the touch position
//            BottleController.throwBottle(startPosition, new Vector2(worldCoordinates.x, worldCoordinates.y), type);
//            //bottleController.throwBottle(worldCoordinates.x, worldCoordinates.y, type);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        return false;
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(float amountX, float amountY) {
//        return false;
//    }
//}
