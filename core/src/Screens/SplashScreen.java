package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen{
    private SpriteBatch batch;
    private Texture logo;
    private Game game;
    private BitmapFont font; // Adiciona um BitmapFont

    private float scale;
    private float screenWidth;
    private float screenHeight;

    private GlyphLayout layout; // Usado para calcular a largura do texto

    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        logo = new Texture("logo.png"); // Substitua pelo caminho correto do seu logo
        font = new BitmapFont(); // Inicializa o BitmapFont
        font.getData().setScale(3); // Ajusta o tamanho do texto, se necessário
        layout = new GlyphLayout(); // Inicializa o GlyphLayout

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        updateScale(); // Atualiza a escala
    }

    private void updateScale() {
        float logoWidth = logo.getWidth();
        float logoHeight = logo.getHeight();
        scale = Math.min(screenWidth / logoWidth, screenHeight / logoHeight);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(logo, Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - logo.getHeight() / 2);

        String text = "Toque em qualquer lugar da tela para continuar";

        layout.setText(font, text); // Calcula a largura do texto

        float textX = (1920 - layout.width) / 2;
        float textY = 60;
        font.draw(batch, layout, textX, textY);
        batch.end();

        if (Gdx.input.justTouched()) { game.setScreen(new MainMenuScreen(game)); }
    }
    @Override
    public void resize(int width, int height) {
        screenWidth = width;
        screenHeight = height;
        updateScale();
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
        font.dispose();
    }
}

