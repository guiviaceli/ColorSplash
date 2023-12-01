package Utils;

import Map.GameMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ColorSplash;

public class GameUI {
    private Stage stage;
    private Skin skin;
    private TiledMap map;

    private TextureRegion playerFaceRegion;
    private TextureRegion playerNameRegion;

    private final Texture backgroundTexture;

    private Texture healthBarTexture;
    private Texture playerTexture;


    public GameUI() {
        playerTexture = new Texture(Gdx.files.internal("Characters/Mage.png"));
        playerFaceRegion = new TextureRegion(playerTexture, 80, 0, 60, 69);
        backgroundTexture = new Texture(Gdx.files.internal("background1.png"));
        healthBarTexture = new Texture(Gdx.files.internal("caminho/para/sua/textura/barra_de_vida.png"));

    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch) {
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playerFaceRegion, 0, 0, 250, 250);
        float healthPercentage = (float) player.getCurrentHealth() / player.getMaxHealth();
        float healthBarWidth = healthPercentage * healthBarTexture.getWidth();

        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

    }
    public void dispose() {
        healthBarTexture.dispose();
        backgroundTexture.dispose();
        playerTexture.dispose();

//        stage.dispose();
//        skin.dispose();
    }


    // Outros métodos da UI conforme necessário...
}
