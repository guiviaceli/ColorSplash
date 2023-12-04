package Screens;

import AnimationEffects.*;
import Bottles.Bottle;
import Utils.*;
import Bottles.RandomBottles;
import Enemy.Enemy;
import Map.GameMap;
import Player.Player;
import Puddles.Puddle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Player.PlayerInputProcessor;
import Enemy.EnemyInputProcessor;

//import static Utils.CollisionHandler.animations;

public class GameScreen implements Screen {
    private Game game;
    private GameMap map;
    private final OrthographicCamera camera;
    ExtendViewport viewport;
    SpriteBatch batch;
    public static Player player;
    public static Enemy player2;
    private static RandomBottles randomBottles;
    private ShapeRenderer shapeRenderer;
    public static List<Sprite> spritesParaRenderizar = new ArrayList<>();
    InputManager inputManager;
    private GameUI gameUI;
    private BitmapFont font;
    private EffectManager effectManager;
    private CollisionHandler collisionHandler;
    ExplosionAnimation explosionAnimation = new ExplosionAnimation();
    FreezeAnimation freezeAnimation = new FreezeAnimation();
    FireAnimation fireAnimation = new FireAnimation();
    WaterAnimation waterAnimation = new WaterAnimation();

    public GameScreen(Game game, int player1Choice, int player2Choice){
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.update();
        this.map = new GameMap("mapa2.tmx");
        font = new BitmapFont();
        font.getData().setScale(3f);

        camera.position.set(map.getMapWidth() / 2f,  map.getMapHeight() / 2f, 0); // Ajusta para o centro do mapa
        viewport = new ExtendViewport(map.getMapWidth(), map.getMapHeight(),camera);
        viewport.apply();
        randomBottles = new RandomBottles(map.getMapWidth(), map.getMapHeight(),10,5);

        PlayerInputProcessor playerInputProcessor = new PlayerInputProcessor();
        player = new Player(map.getMapWidth(), map.getMapHeight(),player1Choice,playerInputProcessor);

        EnemyInputProcessor player2InputProcessor = new EnemyInputProcessor();
        player2 = new Enemy(map.getMapWidth(), map.getMapHeight(),player2Choice,player2InputProcessor);

        effectManager = new EffectManager();
        collisionHandler = new CollisionHandler(effectManager);


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

        String collisionColorPlayer1 = CollisionHandler.checkPuddleCollisions(player, puddles);
        String collisionColorPlayer2 = CollisionHandler.checkPuddleCollisions(player2, puddles);

        AttackEffectHandler.updatePuddles(delta); // Atualiza as poças


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
            puddle.update(delta);
            puddle.draw(batch);
        }
        for (Puddle puddle : player2.getPuddles()) {
            puddle.update(delta);
            puddle.draw(batch);
        }
        for (Sprite sprite : spritesParaRenderizar) {
            sprite.draw(batch);
        }
        player.draw(batch, Gdx.graphics.getDeltaTime());
        player2.draw(batch, Gdx.graphics.getDeltaTime(), font);

        batch.end();

        batch.begin();
        handlePlayerAnimation(collisionColorPlayer1, player, batch);
        handlePlayerAnimation(collisionColorPlayer2, player2, batch);
        batch.end();

        if (player.getCurrentHealth() <= 0) {
            game.setScreen(new GameOverScreen(game, "Player 2", "Player 1"));
        } else if (player2.getCurrentHealth() <= 0) {
            game.setScreen(new GameOverScreen(game, "Player 1", "Player 2"));
        }
    }

    public static void restartGame() {
        player.reset();
        player2.reset();
        randomBottles.reset();
        AttackEffectHandler.resetPuddles();
    }
    private void handlePlayerAnimation(String collisionColor, Utility entity, SpriteBatch batch) {
        if (!collisionColor.isEmpty()) {
            switch (collisionColor) {
                case "BLUE LIGHT":
                    freezeAnimation.setPosition(entity.getX(), entity.getY());
                    freezeAnimation.update(Gdx.graphics.getDeltaTime());
                    freezeAnimation.render(batch);
                    break;
                case "BLUE":
                    waterAnimation.setPosition(entity.getX(), entity.getY());
                    waterAnimation.update(Gdx.graphics.getDeltaTime());
                    waterAnimation.render(batch);
                    break;
                case "RED":
                    fireAnimation.setPosition(entity.getX(), entity.getY());
                    fireAnimation.update(Gdx.graphics.getDeltaTime());
                    fireAnimation.render(batch);
                    break;
                case "BLACK":
                    System.out.println("Entrou aqui");
                    explosionAnimation.setPosition(entity.getX(), entity.getY());
                    explosionAnimation.update(Gdx.graphics.getDeltaTime());
                    explosionAnimation.render(batch);
                    break;
            }
        }
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

    }
    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
        shapeRenderer.dispose();
        gameUI.dispose();
    }
}
