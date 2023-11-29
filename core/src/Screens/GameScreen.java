package Screens;

import Bottles.Bottle;
import Utils.CollisionHandler;
import Bottles.RandomBottles;
import Enemy.Enemy;
import Map.GameMap;
import Player.Player;
import Puddles.Puddle;
//import Utils.PauseMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import Utils.InputManager;
import java.util.ArrayList;
import java.util.List;
import Player.PlayerInputProcessor;
import Enemy.EnemyInputProcessor;
import Utils.AttackEffectHandler;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private Game game;
    private GameMap map;
    private final OrthographicCamera camera;
    ExtendViewport viewport;
    SpriteBatch batch;
    public static Player player;
    public static Enemy player2;
    public static Bottle blackBottle ;
    private RandomBottles randomBottles;
    private ShapeRenderer shapeRenderer;
    public static List<Sprite> spritesParaRenderizar = new ArrayList<>();
    InputManager inputManager;
    private List<Puddle> allPuddles = new ArrayList<>();

    private List<Puddle> allPuddles1 = new ArrayList<>();

    //private PauseMenu pauseMenu;


    public GameScreen(Game game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ExtendViewport(1100, 1100, camera);
        this.camera.update();
        this.map = new GameMap("mapa2.tmx");
        randomBottles = new RandomBottles(map.getMapWidth(), map.getMapHeight(),15,5);

        PlayerInputProcessor playerInputProcessor = new PlayerInputProcessor();
        player = new Player(map.getMapWidth(), map.getMapHeight(),playerInputProcessor);

        EnemyInputProcessor player2InputProcessor = new EnemyInputProcessor();
        player2 = new Enemy(map.getMapWidth(), map.getMapHeight(),player2InputProcessor);

        inputManager = new InputManager();
        inputManager.addProcessor(playerInputProcessor);
        inputManager.addProcessor(player2InputProcessor);
        inputManager.setup();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        //pauseMenu = new PauseMenu(game, new ScreenViewport());


    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        //if (!pauseMenu.isPaused()) {
            float deltaTime = Gdx.graphics.getDeltaTime();
            randomBottles.update(deltaTime);

            CollisionHandler.checkBottleCollisionsPlayer1(player, randomBottles.getBottles());
            CollisionHandler.checkBottleCollisionsPlayer2(player2, randomBottles.getBottles());

            List<Puddle> puddles;
            puddles = AttackEffectHandler.getPuddles();

//            if (puddles.isEmpty()) {
//                    System.out.println("A lista Puddles está vazia.");
//                } else {
//                    System.out.println("Existem " + puddles.size() + " poças na lista Puddles.");
//                }


            CollisionHandler.checkPuddleCollisions(player, puddles);
            CollisionHandler.checkPuddleCollisions(player2, puddles);

//            if (allPuddles.isEmpty()) {
//                System.out.println("A lista allPuddles está vazia.");
//            } else {
//                System.out.println("Existem " + allPuddles.size() + " poças na lista allPuddles.");
//            }


        Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
            batch.begin();
            map.render(camera);
            randomBottles.render(batch);
            for (Puddle puddle : player.getPuddles()) {
                puddle.update();
                puddle.draw(batch);
            }
            for (Puddle puddle : player2.getPuddles()) {
                puddle.update();
                puddle.draw(batch);
            }
            for (Sprite sprite : spritesParaRenderizar) {
                sprite.draw(batch);
            }
            player.draw(batch, Gdx.graphics.getDeltaTime());
            player2.draw(batch, Gdx.graphics.getDeltaTime());

            batch.end();

            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.rect(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height);
            shapeRenderer.end(); // Finalizar a sessão antes de iniciar a próxima
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.rect(player2.getHitbox().x, player2.getHitbox().y, player2.getHitbox().width, player2.getHitbox().height);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(player.getHealthBox().x, player.getHealthBox().y, player.getHealthBox().width, player.getHealthBox().height);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(player2.getHealthBox().x, player2.getHealthBox().y, player2.getHealthBox().width, player2.getHealthBox().height);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);

            for (Puddle puddle : player.getPuddles()) {
                Rectangle hitbox = puddle.getPuddleHitbox();
                shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            }
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLUE);

            for (Puddle puddle : player2.getPuddles()) {
                Rectangle hitbox = puddle.getPuddleHitbox();
                shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            }
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            for (Bottle bottle : randomBottles.getBottles()) {
                shapeRenderer.rect(bottle.getHitbox().x, bottle.getHitbox().y, bottle.getHitbox().width, bottle.getHitbox().height);
            }
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.end();
     }
//        else {
//            pauseMenu.act(Gdx.graphics.getDeltaTime());
//            pauseMenu.draw();
//        }
//
//        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
//            pauseMenu.togglePause();
//        }
   //}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
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

    }
}
