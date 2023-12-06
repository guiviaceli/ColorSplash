package Screens;

import AnimationEffects.*;
import Utils.*;
import Bottles.RandomBottles;
import Enemy.Enemy;
import Map.GameMap;
import Player.Player;
import Puddles.Puddle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import java.util.ArrayList;
import java.util.List;
import Player.PlayerInputProcessor;
import Enemy.EnemyInputProcessor;
import com.mygdx.game.ColorSplash;

import static com.mygdx.game.ColorSplash.manager;

public class GameScreen implements Screen {
    private final Game game;
    private final GameMap map;
    private final OrthographicCamera camera;
    ExtendViewport viewport;
    SpriteBatch batch;
    public static Player player;
    public static Enemy player2;
    private static RandomBottles randomBottles;
    private final ShapeRenderer shapeRenderer;
    public static List<Sprite> spritesParaRenderizar = new ArrayList<>();
    InputManager inputManager;
    private final GameUI gameUI;
    private final BitmapFont font;
    public static List<SimpleAnimation> activeAnimations;
    Texture explosionTexture;
    Texture waterTexture;
    Texture fireTexture;
    Texture freezeTexture;
    private final Music backgroundMusic;

    public GameScreen(Game game, int player1Choice, int player2Choice){
        explosionTexture = manager.get("AnimationEffects/ExplosionAnimation.png", Texture.class);
        waterTexture = manager.get("AnimationEffects/water2.png", Texture.class);
        fireTexture = manager.get("AnimationEffects/FireAnimation.png", Texture.class);
        freezeTexture = manager.get("AnimationEffects/FreezeAnimation.png", Texture.class);
        activeAnimations = new ArrayList<>();
        backgroundMusic = ColorSplash.manager.get("sounds/battleThemeA.mp3", Music.class);

        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.update();
        this.map = new GameMap("mapa2.tmx");
        font = new BitmapFont();
        font.getData().setScale(3f);

        camera.position.set(map.getMapWidth() / 2f,  map.getMapHeight() / 2f, 0);
        viewport = new ExtendViewport(map.getMapWidth(), map.getMapHeight(),camera);
        viewport.apply();
        randomBottles = new RandomBottles(map.getMapWidth(), map.getMapHeight(),10,9);

        PlayerInputProcessor playerInputProcessor = new PlayerInputProcessor();
        player = new Player(map.getMapWidth(), map.getMapHeight(),player1Choice,playerInputProcessor);

        EnemyInputProcessor player2InputProcessor = new EnemyInputProcessor();
        player2 = new Enemy(map.getMapWidth(), map.getMapHeight(),player2Choice,player2InputProcessor);

        inputManager = new InputManager();
        inputManager.addProcessor(playerInputProcessor);
        inputManager.addProcessor(player2InputProcessor);
        inputManager.setup();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        gameUI = new GameUI(player1Choice, player2Choice);
    }
    @Override
    public void show() {
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.3f);
        backgroundMusic.play();
    }
    @Override
    public void render(float delta) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        randomBottles.update(deltaTime);

        CollisionHandler.checkBottleCollisions(player, randomBottles.getBottles());
        CollisionHandler.checkBottleCollisions(player2, randomBottles.getBottles());

        List<Puddle> puddles;
        puddles = AttackEffectHandler.getPuddles();

        CollisionHandler.checkPuddleCollisions(player, puddles);

        CollisionHandler.checkPuddleCollisions(player2, puddles);


        AttackEffectHandler.updatePuddles(deltaTime);


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.begin();
        gameUI.draw(batch, player, player2);
        batch.end();

        batch.begin();
        map.render(camera);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        randomBottles.render(batch);


        for (Puddle puddle : player.getPuddles()) {
            puddle.update(deltaTime);
            puddle.draw(batch);
        }
        for (Puddle puddle : player2.getPuddles()) {
            puddle.update(deltaTime);
            puddle.draw(batch);
        }
        for (Sprite sprite : spritesParaRenderizar) {
            sprite.draw(batch);
        }

        player.draw(batch, Gdx.graphics.getDeltaTime());
        player2.draw(batch, Gdx.graphics.getDeltaTime(), font);

        batch.end();

        if (activeAnimations != null) {
            for (int i = activeAnimations.size() - 1; i >= 0; i--) {
                SimpleAnimation animation = activeAnimations.get(i);
                animation.update(deltaTime);
                batch.begin();
                animation.draw(batch);
                batch.end();

                if (animation.isAnimationFinished()) {
                    activeAnimations.remove(i);
                }
            }
        }

        if (player.getCurrentHealth() <= 0) {
            game.setScreen(new VictoryScreen(game, "Player 2", "Player 1"));
        } else if (player2.getCurrentHealth() <= 0) {
            game.setScreen(new VictoryScreen(game, "Player 1", "Player 2"));
        }
    }

    public static void restartGame() {
        player.reset();
        player2.reset();
        randomBottles.reset();
        AttackEffectHandler.resetPuddles();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        Camera mapCamera = viewport.getCamera();
        mapCamera.position.set(map.getMapWidth() / 2f,  map.getMapHeight() / 2f, 0);
        mapCamera.update();
    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {
        backgroundMusic.stop();
    }
    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
        shapeRenderer.dispose();
        gameUI.dispose();
        backgroundMusic.dispose();
    }
}
