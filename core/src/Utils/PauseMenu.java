package Utils;

import Screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PauseMenu extends Stage {
    private Stage stage;
    private final Game finalGame; // Referência ao jogo principal
    private boolean isPaused;


    public PauseMenu(Game game, Viewport viewport) {
        super(viewport);
        this.finalGame = game;
        stage = new Stage();
        initializeMenu();
    }
    private void initializeMenu() {
        Texture upTexture = new Texture(Gdx.files.internal("blue-.png")); // Caminho para a imagem no estado normal
        TextureRegion upRegion = new TextureRegion(upTexture);
        Texture downTexture = new Texture(Gdx.files.internal("red-.png")); // Caminho para a imagem no estado pressionado
        TextureRegion downRegion = new TextureRegion(downTexture);
        BitmapFont buttonFont = new BitmapFont(); // Você pode usar uma fonte personalizada aqui
        buttonFont.getData().setScale(3); // Aumenta o tamanho da fonte

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;

        TextButton resumeButton = new TextButton("Continuar", style);
        resumeButton.setSize(500, 200); // Definir o tamanho do botão
        resumeButton.setPosition((1280 - 500) / 2, (960 / 2) - 100);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isPaused = false;
            }
        });

        TextButton restartButton  = new TextButton("Reiniciar", style);
        restartButton .setSize(500, 200); // Definir o tamanho do botão
        restartButton .setPosition((1280 - 500) / 2, (960 / 2) - 250);
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isPaused = false;
                finalGame.setScreen(new GameScreen(finalGame));
            }
        });

        TextButton exitButton   = new TextButton("Sair", style);
        exitButton  .setSize(500, 200); // Definir o tamanho do botão
        exitButton  .setPosition((1280 - 500) / 2, (960 / 2) - 450);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit(); // Fecha o aplicativo
            }
        });

        stage.addActor(resumeButton);
        stage.addActor(restartButton);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage); // Definir o processador de entrada

    }

    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            Gdx.input.setInputProcessor(this);
        } else {
            Gdx.input.setInputProcessor(null); // ou definir para o input processor padrão do jogo
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public void act(float delta) {
        if (isPaused) {
            super.act(delta);
        }
    }

    @Override
    public void draw() {
        if (isPaused) {
            super.draw();
        }
    }

    public void render() {
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
