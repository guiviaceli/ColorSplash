package Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameMap {
    private static TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    private int mapWidth, mapHeight;


    public GameMap(String mapPath) {
        tiledMap = new TmxMapLoader().load(mapPath);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        mapWidth = layer.getWidth() * (int) layer.getTileWidth();
        mapHeight = layer.getHeight() * (int) layer.getTileHeight();
    }

    public void render(OrthographicCamera tiledCamera) {
        tiledMapRenderer.setView(tiledCamera);
        tiledMapRenderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }
    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}