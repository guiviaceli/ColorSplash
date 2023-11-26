package Screens;

import Bottles.Bottle;
import Bottles.RandomBottles;
import Map.GameMap;
import Player.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen implements Screen {
    private Game game;
    private GameMap map;
    private final OrthographicCamera camera;
    ExtendViewport viewport;
    SpriteBatch batch;
    public static Player player;
    public static Bottle blackBottle ;
    private RandomBottles randomBottles;
    private ShapeRenderer shapeRenderer;

    public GameScreen(Game game) {

        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ExtendViewport(1100, 1100, camera);
        this.camera.update();
        this.map = new GameMap("mapa2.tmx");
        randomBottles = new RandomBottles(map.getMapWidth(), map.getMapHeight(),15,5);
        player = new Player(map.getMapWidth(), map.getMapHeight());
        batch = new SpriteBatch();
        blackBottle = new Bottle(0); // 0 para a garrafa preta

        shapeRenderer = new ShapeRenderer();

    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        randomBottles.update(deltaTime);

        checkBottleCollisions();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.begin();
        map.render(camera);
        randomBottles.render(batch);
        blackBottle.draw(batch, 30,45); // x e y são as coordenadas onde você quer desenhar a garrafa
        player.draw(batch, Gdx.graphics.getDeltaTime());
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Desenha o hitbox do jogador
        shapeRenderer.rect(player.getHitbox().x, player.getHitbox().y,
                player.getHitbox().width, player.getHitbox().height);

        // Desenha os hitboxes das garrafas
        for (Bottle bottle : randomBottles.getBottles()) {
            shapeRenderer.rect(bottle.getHitbox().x, bottle.getHitbox().y,
                    bottle.getHitbox().width, bottle.getHitbox().height);
        }

        shapeRenderer.end();
    }

    private void checkBottleCollisions() {
        for (Bottle bottle : randomBottles.getBottles()) {
            if (player.getHitbox().overlaps(bottle.getHitbox()) && !player.hasBottle()) {
                // O jogador colidiu com a garrafa e não tem uma garrafa atualmente
                System.out.println("Colisão detectada com a garrafa!");
                player.collectBottle(bottle);
                //player.setCollectibleBottle(bottle); // Configura a garrafa que o jogador pode coletar

            }
        }
    }

    private void collectBottle(Bottle bottle) {
        // Lógica para coletar a garrafa
        System.out.println("Coletando a garrafa: " + bottle);
        randomBottles.removeBottle(bottle);
        player.setHasBottle(true);
        // Aqui você pode incrementar a pontuação do jogador, tocar um som, etc.
    }
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
