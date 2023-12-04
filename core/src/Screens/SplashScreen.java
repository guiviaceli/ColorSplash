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
    private Texture logo1;
    private Texture logoEstudio;
    private Game game;
    private BitmapFont font;
    private float scale;
    private float screenWidth;
    private float screenHeight;
    private GlyphLayout layout;
    private float alpha;
    private float tempo;
    private final float DURACAO_FADE = 2.0f;
    private enum Estado {
        FADE_IN_LOGO_ESTUDIO, INTERVALO_LOGO_ESTUDIO, FADE_OUT_LOGO_ESTUDIO,
        FADE_IN_LOGO_JOGO, FADE_OUT_LOGO_JOGO, FINALIZADO
    }
    private Estado estadoAtual;
    private final float INTERVALO = 1.0f;
    private String textoPresents = "PRESENTS";

    private float escalaLogoEstudio;
    private float escalaLogoGame;


    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        logo1 = new Texture("logo1.png");
        logoEstudio = new Texture("LogoStudioSemFundo.png");

        font = new BitmapFont();
        font.getData().setScale(3);
        layout = new GlyphLayout();
        estadoAtual = Estado.FADE_IN_LOGO_ESTUDIO;
        alpha = 0.0f;

        layout = new GlyphLayout(font,textoPresents);

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        escalaLogoEstudio = calcularEscala(logoEstudio);
        escalaLogoGame = calcularEscala(logo1);
    }

    private void updateScale() {
        float logoWidth = logo1.getWidth();
        float logoHeight = logo1.getHeight();
        scale = Math.min(screenWidth / logoWidth, screenHeight / logoHeight);
    }
    private float calcularEscala(Texture logo) {
        float larguraDesejada = Gdx.graphics.getWidth() * 0.6f;
        return larguraDesejada / (float) logo.getWidth();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.setColor(1, 1, 1, alpha);
        if (estadoAtual == Estado.FADE_IN_LOGO_ESTUDIO ||
                estadoAtual == Estado.INTERVALO_LOGO_ESTUDIO ||
                estadoAtual == Estado.FADE_OUT_LOGO_ESTUDIO) {

            float logoWidth = logoEstudio.getWidth() * escalaLogoEstudio;
            float logoHeight = logoEstudio.getHeight() * escalaLogoEstudio;

            batch.draw(logoEstudio, (Gdx.graphics.getWidth() - logoWidth) / 2,
                    (Gdx.graphics.getHeight() - logoHeight) / 2,
                    logoWidth, logoHeight);
            layout.setText(font, textoPresents);
            float textX = (1920 - layout.width) / 2;
            float textY =40;
            font.draw(batch, layout, textX, textY);
        } else if (estadoAtual == Estado.FADE_IN_LOGO_JOGO ||
                estadoAtual == Estado.FADE_OUT_LOGO_JOGO) {

            float logoWidth1 = logo1.getWidth() * escalaLogoGame;
            float logoHeight1 = logo1.getHeight() * escalaLogoGame;

            batch.draw(logo1, (Gdx.graphics.getWidth() - logoWidth1) / 2,
                    (Gdx.graphics.getHeight() - logoHeight1) / 2,
                    logoWidth1, logoHeight1);
        }
        batch.end();
        atualizarAlpha(delta);
    }

    @Override
    public void resize(int width, int height) {

    }
    private void atualizarAlpha(float delta) {
        switch (estadoAtual) {
            case FADE_IN_LOGO_ESTUDIO:
                alpha += delta / DURACAO_FADE;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    estadoAtual = Estado.INTERVALO_LOGO_ESTUDIO;
                    tempo = 0;
                }
                break;
            case INTERVALO_LOGO_ESTUDIO:
                tempo += delta;
                if (tempo >= INTERVALO) {
                    estadoAtual = Estado.FADE_OUT_LOGO_ESTUDIO;
                }
                break;
            case FADE_OUT_LOGO_ESTUDIO:
                alpha -= delta / DURACAO_FADE;
                if (alpha <= 0.0f) {
                    alpha = 0.0f;
                    estadoAtual = Estado.FADE_IN_LOGO_JOGO;
                }
                break;
            case FADE_IN_LOGO_JOGO:
                alpha += delta / DURACAO_FADE;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    estadoAtual = Estado.FADE_OUT_LOGO_JOGO;
                }
                break;
            case FADE_OUT_LOGO_JOGO:
                alpha -= delta / DURACAO_FADE;
                if (alpha <= 0.0f) {
                    alpha = 0.0f;
                    estadoAtual = Estado.FINALIZADO;
                }
                break;
            case FINALIZADO:
                game.setScreen(new MainMenuScreen(game));
                break;
        }
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
        logo1.dispose();
        logoEstudio.dispose();
        font.dispose();
    }
}

