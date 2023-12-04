package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class CharacterSelectScreen implements Screen {
    private Game game;
    private Texture[] characterSheets;
    private int currentPlayer;
    private int player1Choice, player2Choice;
    private SpriteBatch batch;
    private CharacterIcon[] characterIcons;
    private final int CHARACTERS_PER_ROW = 6;
    private final int PADDING = 20;
    int iconWidth = 100;
    int iconHeight = 100;
    private int tempPlayer1Choice = -1;
    private int tempPlayer2Choice = -1;
    private Stage stage;
    private static final int FRAME_WIDTH = 24;
    private static final int FRAME_HEIGHT = 32;
    private String textoChoice= "Selecione um personagem:";
    private String textoPlayer1 = "PLAYER 1";
    private String textoPlayer2 = "PLAYER 2";
    private GlyphLayout layout;
    private GlyphLayout layout1;
    private GlyphLayout layout2;
    private BitmapFont font;

    public CharacterSelectScreen(final Game game) {
        this.game = game;
        currentPlayer = 1;
        this.batch = new SpriteBatch();
        this.characterIcons = new CharacterIcon[39];
        this.characterSheets = new Texture[39];
        stage = new Stage();
        font = new BitmapFont();
        font.getData().setScale(3);
        layout = new GlyphLayout();
        layout1 = new GlyphLayout();
        layout2 = new GlyphLayout();

        for (int i = 0; i < characterSheets.length; i++) {
            characterSheets[i] = new Texture(Gdx.files.internal("Characters/Character" + i + ".png"));
            int regionX = 80;
            int regionY = 0;
            int regionWidth = 60;
            int regionHeight = 69;

            TextureRegion region = new TextureRegion(characterSheets[i], regionX, regionY, regionWidth, regionHeight);

            float x = (i % CHARACTERS_PER_ROW) * (regionWidth + PADDING);
            float y = (i / CHARACTERS_PER_ROW) * (regionHeight + PADDING);
            characterIcons[i] = new CharacterIcon(region, x, y);
        }

        Texture upTexture = new Texture(Gdx.files.internal("blue-.png"));
        TextureRegion upRegion = new TextureRegion(upTexture);
        Texture downTexture = new Texture(Gdx.files.internal("red-.png"));
        TextureRegion downRegion = new TextureRegion(downTexture);
        BitmapFont buttonFont = new BitmapFont();
        buttonFont.getData().setScale(3);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;
        TextButton selectButton = new TextButton("Selecionar", style);
        selectButton.setSize(250, 150);
        selectButton.setPosition((1850 + 300) / 2, 35);
        selectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentPlayer == 1 && tempPlayer1Choice != -1) {
                    player1Choice = tempPlayer1Choice;
                    tempPlayer1Choice = -1;
                    currentPlayer = 2;
                } else if (currentPlayer == 2 && tempPlayer2Choice != -1) {
                    player2Choice = tempPlayer2Choice;
                    tempPlayer2Choice = -1;
                    if (player1Choice != -1 && player2Choice != -1) {
                        game.setScreen(new GameScreen(game, player1Choice, player2Choice));
                    }
                }
            }

        });
        stage.addActor(selectButton);
        Gdx.input.setInputProcessor(stage);

    }

    private class CharacterIcon {
        public TextureRegion region;
        public Rectangle hitbox;

        public CharacterIcon(TextureRegion region, float x, float y) {
            this.region = region;
            this.hitbox = new Rectangle(x, y, region.getRegionWidth(), region.getRegionHeight());
        }
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        int rows = (int)Math.ceil((float)characterIcons.length / CHARACTERS_PER_ROW);
        int totalWidth = CHARACTERS_PER_ROW * iconWidth + (CHARACTERS_PER_ROW - 1) * PADDING;
        int totalHeight = rows * iconHeight + (rows - 1) * PADDING;
        float startingX = (screenWidth - totalWidth) / 2;
        float startingY = (screenHeight - totalHeight) / 2;

        batch.begin();
        layout = new GlyphLayout(font,textoChoice);
        layout.setText(font, textoChoice);
        float textX = (1920 - layout.width) / 2;
        float textY =930;
        font.draw(batch, layout, textX, textY);
        for (int i = 0; i < characterIcons.length; i++) {
            float x = startingX + (i % CHARACTERS_PER_ROW) * (iconWidth + PADDING);
            float y = startingY + (i / CHARACTERS_PER_ROW) * (iconHeight + PADDING);
            y = screenHeight - y - iconHeight;
            batch.draw(characterIcons[i].region, x, y, iconWidth, iconHeight);
            characterIcons[i].hitbox.setPosition(x, y);
        }
        batch.end();

        batch.begin();
        layout1 = new GlyphLayout(font,textoPlayer1);
        layout1.setText(font, textoPlayer1);
        float textX1 =125;
        float textY1 =700;
        font.draw(batch, layout1, textX1, textY1);
        batch.end();

        batch.begin();
        layout2 = new GlyphLayout(font,textoPlayer2);
        layout2.setText(font, textoPlayer2);
        float textX2 =1600;
        float textY2 =700;
        font.draw(batch, layout2, textX2, textY2);
        batch.end();

        batch.begin();
        if (tempPlayer1Choice != -1) {

            Texture characterTexture = characterSheets[tempPlayer1Choice];
            TextureRegion selectedSprite = new TextureRegion(characterTexture, 2*FRAME_WIDTH, 2 * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
            float x = Gdx.graphics.getWidth() - FRAME_WIDTH * 3;
            float y = Gdx.graphics.getHeight() - FRAME_HEIGHT * 3;
            float scale = 10.0f;
            batch.draw(selectedSprite, 50, 300, FRAME_WIDTH * scale, FRAME_HEIGHT * scale);
        }

        if (tempPlayer2Choice != -1) {
            Texture characterTexture = characterSheets[tempPlayer2Choice];
            TextureRegion selectedSprite = new TextureRegion(characterTexture, 2*FRAME_WIDTH, 2 * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);

            float x = Gdx.graphics.getWidth() - FRAME_WIDTH * 3;
            float y = Gdx.graphics.getHeight() - FRAME_HEIGHT * 3;
            float scale = 10.0f;
            batch.draw(selectedSprite, 1600, 300, FRAME_WIDTH * scale, FRAME_HEIGHT * scale);
        }
        batch.end();

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos.y = screenHeight - touchPos.y;

            for (int i = 0; i < characterIcons.length; i++) {
                Rectangle hitbox = characterIcons[i].hitbox;

                if (hitbox.contains(touchPos.x, touchPos.y)) {
                    if (currentPlayer == 1) {
                        tempPlayer1Choice  = i;
                    } else if (currentPlayer == 2) {
                        tempPlayer2Choice = i;
                    }
                    break;
                }
            }
        }
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {

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
        stage.dispose();

    }
}

