package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameOverScreen implements Screen {
    private Game game;
    private SpriteBatch batch;
    private BitmapFont font;
    private String winner;
    private Stage stage;
    private String looser;
    private Texture backgroundImage;

    public GameOverScreen(final Game game, String winner, String looser) {
        this.game = game;
        this.winner = winner;
        this.looser = looser;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        backgroundImage = new Texture(Gdx.files.internal("GameOver.png"));

        Texture upTexture = new Texture(Gdx.files.internal("blue-.png"));
        TextureRegion upRegion = new TextureRegion(upTexture);
        Texture downTexture = new Texture(Gdx.files.internal("red-.png"));
        TextureRegion downRegion = new TextureRegion(downTexture);
        BitmapFont buttonFont = new BitmapFont();
        buttonFont.getData().setScale(3);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;

        TextButton restartButton = new TextButton("Novo Jogo",style);
        restartButton.setSize(300, 180);
        restartButton.setPosition(350, 65);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //CharacterSelectScreen characterSelectScreen = new CharacterSelectScreen(game);
                GameScreen.restartGame();
                game.setScreen(new CharacterSelectScreen(game));
            }
        });

        TextButton mainMenuButton = new TextButton("Menu Principal", style);
        mainMenuButton.setSize(300, 180);
        mainMenuButton.setPosition(850,  65);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreen.restartGame();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        TextButton exitButton = new TextButton("Sair", style);
        exitButton.setSize(300, 180);
        exitButton.setPosition(1350,  65);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(restartButton);
        stage.addActor(mainMenuButton);
        stage.addActor(exitButton);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "Game Over! Você perdeu, " + looser +"! Parabéns pela vitória "+ winner + "!", 300, 45);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
        font.dispose();
        stage.dispose();
    }
}
