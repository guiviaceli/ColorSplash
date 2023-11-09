package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen implements Screen{
    private SpriteBatch batch;
    private Texture logo;
    private Game game;

    private long startTime;
    private static final long SPLASH_TIME = 3000; // Tempo de splash em milissegundos (3 segundos)

    public SplashScreen(Game game) {
        this.game = game;
        startTime = TimeUtils.millis(); // Captura o tempo quando a tela é criado
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        logo = new Texture("logo.png"); // Substitua pelo caminho correto do seu logo
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(logo, Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - logo.getHeight() / 2);
        batch.end();

        // Aqui você pode adicionar uma condição para mudar para a tela do menu após alguns segundos ou ao tocar na tela
        // Exemplo: if (Gdx.input.justTouched()) { game.setScreen(new MenuScreen(game)); }
//        if (TimeUtils.millis() > (startTime + SPLASH_TIME)) {
//            game.setScreen(new MainMenuScreen(game)); // Muda para o MainMenuScreen
//            dispose(); // Não esqueça de chamar dispose para liberar os recursos da SplashScreen
//        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        logo.dispose();
    }
}

