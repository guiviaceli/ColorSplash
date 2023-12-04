package Utils;

import Bottles.Bottle;
import Puddles.Puddle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.Arrays;
import java.util.List;

public class GameUI implements Utility{
    private TextureRegion playerFaceRegion;
    private TextureRegion player2FaceRegion;
    private final Texture backgroundTexture;
    private Texture playerTexture;
    private Texture player2Texture;
    private ShapeRenderer shapeRenderer;
    private static final int HEALTH_BAR_WIDTH = 250;
    private static final int HEALTH_BAR_HEIGHT = 20;
    private static final int PLAYER1_HEALTH_BAR_X = 220;
    private static final int PLAYER1_HEALTH_BAR_Y = Gdx.graphics.getHeight() - 825;
    private static final int PLAYER2_HEALTH_BAR_X = Gdx.graphics.getWidth() - 450;
    private static final int PLAYER2_HEALTH_BAR_Y = Gdx.graphics.getHeight() - 825;
    private BitmapFont font;
    private List<String> characterNames;
    private int choice1, choice2;

    public GameUI(int characterChoice1, int characterChoice2) {
        characterNames = Arrays.asList("Velhote", "Camponesa", "Mocinha", "Bailarina", "Palhaço", "Misteriosa", "Dançarina",
                "Lutadora","Mago", "Lutador", "Grama", "Curandeira", "Enfermeira", "Reizinho", "Maga", "Tiazinha", "Cogumelo do Mario",
            "Artista", "Pirata", "Frutinha", "Rainha", "Princesa", "Arqueira", "Arqueiro", "Tronquilho", "Papai Noel", "Roxane", "Xaiane"
                , "Ruivo", "Moikano", "Carec", "Boo","Estilo", "Sheila", "Nikita", "Roxono", "Velhota", "Mestre Kame", "Lutador");
        font = new BitmapFont();
        font.getData().setScale(3f);

        playerTexture = new Texture(Gdx.files.internal("Characters/Character" + characterChoice1 + ".png"));
        player2Texture = new Texture(Gdx.files.internal("Characters/Character" + characterChoice2 + ".png"));

        playerFaceRegion = new TextureRegion(playerTexture, 80, 0, 60, 69);
        player2FaceRegion = new TextureRegion(player2Texture, 80, 0, 60, 69);
        backgroundTexture = new Texture(Gdx.files.internal("background1.png"));

        shapeRenderer = new ShapeRenderer();
        choice1 = characterChoice1;
        choice2 = characterChoice2;
    }
    public void draw(SpriteBatch batch, Utility entity, Utility entity1) {
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playerFaceRegion, 0, 0, 250, 250);
        batch.draw(player2FaceRegion, 1800, 0, 250, 250);

        if (entity.hasBottle()) {

        Bottle collectibleBottlePlayer1 = entity.getCollectibleBottle();
        if (collectibleBottlePlayer1 != null) {
            final Sprite bottleTexture = new Sprite(collectibleBottlePlayer1.getTexture());
            batch.draw(bottleTexture, 280, PLAYER1_HEALTH_BAR_Y - 115, 50 , 100);
        }}
        batch.end();
        batch.begin();
        if (entity1.hasBottle()) {

        Bottle collectibleBottlePlayer2 = entity1.getCollectibleBottle();
        if (collectibleBottlePlayer2 != null) {
            final Sprite bottleTexture1 = new Sprite(collectibleBottlePlayer2.getTexture());
            batch.draw(bottleTexture1, PLAYER2_HEALTH_BAR_X + 150, PLAYER2_HEALTH_BAR_Y - 115 , 50, 100);
        }}

        String name1 = characterNames.get(choice1);
        font.draw(batch, name1, 10, PLAYER1_HEALTH_BAR_Y + 150);

        String name2 = characterNames.get(choice2);
        font.draw(batch, name2, PLAYER2_HEALTH_BAR_X + 190, PLAYER2_HEALTH_BAR_Y +150);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.GREEN);
        float healthPercentagePlayer1 = (float) entity.getCurrentHealth() / entity.getMaxHealth();
        float healthBarWidthPlayer1 = healthPercentagePlayer1 * HEALTH_BAR_WIDTH;
        shapeRenderer.rect(PLAYER1_HEALTH_BAR_X, PLAYER1_HEALTH_BAR_Y, healthBarWidthPlayer1, HEALTH_BAR_HEIGHT);

        shapeRenderer.setColor(Color.RED);
        float healthPercentagePlayer2 = (float) entity1.getCurrentHealth() / entity1.getMaxHealth();
        float healthBarWidthPlayer2 = healthPercentagePlayer2 * HEALTH_BAR_WIDTH;
        shapeRenderer.rect(PLAYER2_HEALTH_BAR_X, PLAYER2_HEALTH_BAR_Y, healthBarWidthPlayer2, HEALTH_BAR_HEIGHT);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(PLAYER1_HEALTH_BAR_X, PLAYER1_HEALTH_BAR_Y, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        shapeRenderer.rect(PLAYER2_HEALTH_BAR_X, PLAYER2_HEALTH_BAR_Y, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);

        shapeRenderer.end();

        batch.begin();
    }
    public void dispose() {
        backgroundTexture.dispose();
        playerTexture.dispose();
        player2Texture.dispose();
    }

    @Override
    public List<Puddle> getPuddles() {
        return null;
    }

    @Override
    public Rectangle getHitbox() {
        return null;
    }

    @Override
    public Rectangle getHealthBox() {
        return null;
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public void updateHealthBar() {

    }

    @Override
    public boolean hasBottle() {
        return false;
    }

    @Override
    public void collectBottle(Bottle bottle) {

    }

    @Override
    public int getMaxHealth() {
        return 0;
    }

    @Override
    public int getCurrentHealth() {
        return 0;
    }

    @Override
    public Bottle getCollectibleBottle() {
        return null;
    }

    @Override
    public void freeze(float duration) {

    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }


    // Outros métodos da UI conforme necessário...
}
