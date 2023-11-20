//package Screens;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;
//import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//
//public class GameScreen implements Screen {
//
//    private Game game;
//    private TiledMap tiledMap;
//    private OrthogonalTiledMapRenderer tiledMapRenderer;
//    private OrthographicCamera tiledCamera;
//
//    public GameScreen(Game game) {
//        this.game = game;
//        // Configura a câmera
//        tiledCamera = new OrthographicCamera();
//        tiledCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        tiledCamera.update();
//
//        // Carrega o mapa
//        tiledMap = new TmxMapLoader().load("mapa2.tmx");
//        // Cria o renderizador do mapa
//        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
//    }
//
//    @Override
//    public void show() {
//        // Inicializações quando a tela é mostrada
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Atualiza a câmera
//        tiledCamera.update();
//        tiledMapRenderer.setView(tiledCamera);
//        // Renderiza o Mapa
//        tiledMapRenderer.render();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        tiledCamera.viewportWidth = width;
//        tiledCamera.viewportHeight = height;
//        tiledCamera.update();
//    }
//    @Override
//    public void pause() {
//        // Código para quando o jogo é pausado
//    }
//
//    @Override
//    public void resume() {
//        // Código para quando o jogo é retomado
//    }
//
//    @Override
//    public void hide() {
//        // Código para quando a tela é escondida
//    }
//    @Override
//    public void dispose() {
//        tiledMap.dispose();
//        tiledMapRenderer.dispose();
//    }
//
//}

package Screens;

import Map.GameMap;
import Player.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Player.PlayerCamera;

public class GameScreen implements Screen {
    private Game game;
    private GameMap map;
    //private final OrthographicCamera camera;
    private PlayerCamera playerCamera;


    SpriteBatch batch;
    public static Player player;

//    public void create() {
//
////        playerInputProcessor = new PlayerInputProcessor();
////        Gdx.input.setInputProcessor(playerInputProcessor);
//    }
    public GameScreen(Game game) {

        this.game = game;
//        this.camera = new OrthographicCamera();
//        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        this.camera.update();

        this.map = new GameMap("mapa2.tmx");
        player = new Player(map.getMapWidth(), map.getMapHeight());
        playerCamera = new PlayerCamera(800, 600);
        batch = new SpriteBatch();
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        playerCamera.update(player);

        // Configurar o batch para usar a matriz de projeção da câmera
        batch.setProjectionMatrix(playerCamera.getCamera().combined);

        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Atualizar a câmera com a posição do jogador

        //camera.update();
        //map.render(camera);
        map.render(playerCamera.getCamera()); // Renderizar o mapa usando a câmera do jogador

        player.draw(batch, Gdx.graphics.getDeltaTime());
        batch.end();
    }
    @Override
    public void resize(int width, int height) {
//        camera.viewportWidth = width;
//        camera.viewportHeight = height;
//        camera.update();
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
    }
}
