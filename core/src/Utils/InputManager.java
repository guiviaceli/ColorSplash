package Utils;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;

public class InputManager extends InputMultiplexer{

    private final InputMultiplexer inputMultiplexer;

    public InputManager() {
        inputMultiplexer = new InputMultiplexer();
    }

    public void addProcessor(InputProcessor processor) {
        inputMultiplexer.addProcessor(processor);
        //super.addProcessor(processor);
        //Gdx.input.setInputProcessor(processor);
    }
    public void setup() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
