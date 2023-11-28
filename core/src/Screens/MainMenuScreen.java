
package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class MainMenuScreen implements Screen {

    private Stage stage;
    private Game game; // Usado para mudar de telas
    private SpriteBatch batch;
    private Texture background; // Textura para a imagem de fundo
    private Texture title;      // Imagem do título


    public MainMenuScreen(Game game) {
        this.game = game;
        final Game finalGame = game; // Cria uma cópia final da variável game
        stage = new Stage();

        Texture upTexture = new Texture(Gdx.files.internal("blue-.png")); // Caminho para a imagem no estado normal
        TextureRegion upRegion = new TextureRegion(upTexture);
        Texture downTexture = new Texture(Gdx.files.internal("red-.png")); // Caminho para a imagem no estado pressionado
        TextureRegion downRegion = new TextureRegion(downTexture);
        BitmapFont buttonFont = new BitmapFont(); // Você pode usar uma fonte personalizada aqui
        buttonFont.getData().setScale(3); // Aumenta o tamanho da fonte

        TextButtonStyle style = new TextButtonStyle();
        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;

        Gdx.input.setInputProcessor(stage); // Definir o processador de entrada para receber eventos de toque/click

        TextButton playButton = new TextButton("Jogar", style);
        playButton.setSize(500, 200); // Definir o tamanho do botão
        playButton.setPosition((1280 - 500) / 2, (960 / 2) - 100);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                finalGame.setScreen(new GameScreen(finalGame)); // Muda para a tela do jogo
            }
        });

        TextButton exitButton = new TextButton("Sair", style);
        exitButton.setSize(500, 200); // Definir o tamanho do botão
        exitButton.setPosition((1280 - 500) / 2, (960 / 2) - 250);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit(); // Fecha o aplicativo
            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background.png"));
        title = new Texture(Gdx.files.internal("nome1.png")); // Carrega a imagem do título

        // Este método é chamado quando a tela é definida como atual
    }

    @Override
    public void render(float delta) {
        // Limpa a tela e desenha os elementos UI
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Desenha a imagem do título
        float titleX = (Gdx.graphics.getWidth() - title.getWidth()) / 2; // Centraliza o título horizontalmente
        float titleY = Gdx.graphics.getHeight() - title.getHeight() - 20; // 20 pixels abaixo do topo da tela
        batch.draw(title, titleX, titleY);

        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        background.dispose();
        title.dispose(); // Libera a textura do título
    }
}
