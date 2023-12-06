package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ColorSplash;

public class MainMenuScreen implements Screen {
    private final Stage stage;
    private final Game game;
    private SpriteBatch batch;
    private Texture background;
    private Texture title;
    private final Music backgroundMusic;


    public MainMenuScreen(Game game) {
        this.game = game;
        final Game finalGame = game;
        stage = new Stage();

        backgroundMusic = ColorSplash.manager.get("sounds/MainMenu.mp3", Music.class);



        Texture upTexture = new Texture(Gdx.files.internal("blue-.png"));
        TextureRegion upRegion = new TextureRegion(upTexture);
        Texture downTexture = new Texture(Gdx.files.internal("red-.png"));
        TextureRegion downRegion = new TextureRegion(downTexture);
        BitmapFont buttonFont = new BitmapFont();
        buttonFont.getData().setScale(3);

        TextButtonStyle style = new TextButtonStyle();
        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;

        Gdx.input.setInputProcessor(stage);

        TextButton playButton = new TextButton("Jogar", style);
        playButton.setSize(500, 200);
        playButton.setPosition((1920 - 500) / 2, (960 / 2) - 100);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                finalGame.setScreen(new CharacterSelectScreen(finalGame));
            }
        });

        TextButton exitButton = new TextButton("Sair", style);
        exitButton.setSize(500, 200);
        exitButton.setPosition((1920 - 500) / 2, (960 / 2) - 250);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background.png"));
        title = new Texture(Gdx.files.internal("nome1.png"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.3f);
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float titleX = (Gdx.graphics.getWidth() - title.getWidth()) / 2;
        float titleY = Gdx.graphics.getHeight() - title.getHeight() - 20;
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
    public void hide() {
        backgroundMusic.stop();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        background.dispose();
        title.dispose();
        backgroundMusic.dispose();

    }
}