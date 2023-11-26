package Player;

import Bottles.Bottle;
import Bottles.RandomBottles;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ColorSplash;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Sprite {
    private float mapWidth, mapHeight;
    PlayerInputProcessor playerInputProcessor;
    private static final int FRAME_WIDTH = 24;
    private static final int FRAME_HEIGHT = 32;
    private float x, y;
    private float speed = 300;
    private int frameRow;
    private int frameCol;
    private static final int FRAMES_PER_DIRECTION = 3;
    private Animation<TextureRegion>[] animations;
    private float stateTime;
    private TextureRegion currentFrame;
    private boolean hasBottle; // Indica se o jogador está carregando uma garrafa
    private Rectangle playerHitbox;
    private Bottle collectibleBottle; // Garrafa que pode ser coletada

    public Player(int mapWidth, int mapHeight){
        super(ColorSplash.manager.<Texture>get("Mage.png"));
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        playerInputProcessor = new PlayerInputProcessor();
        ColorSplash.addInputProcessor(playerInputProcessor);
        this.frameRow = 0;
        this.frameCol = 0;
        setRegion(frameCol * FRAME_WIDTH, frameRow * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
        this.x = 0;
        this.y = 0;
        this.setPosition(this.x, this.y);

        playerHitbox = new Rectangle(x, y, getWidth() - 30, getHeight() - 10);


        animations = new Animation[4];
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 4; i++) {
            frames.clear();
            for (int j = 0; j < FRAMES_PER_DIRECTION; j++) {
                frames.add(new TextureRegion(getTexture(), j * FRAME_WIDTH, i * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT));
            }
            animations[i] = new Animation<TextureRegion>(0.1f, frames, Animation.PlayMode.LOOP);
        }
        stateTime = 0f;
        currentFrame = getFrame();

        this.hasBottle = false; // Inicializa o jogador sem uma garrafa
    }
    private TextureRegion getFrame() {
        return animations[frameRow].getKeyFrame(stateTime);
    }
    public void draw(SpriteBatch batch, float delta) {
        this.setPosition(x,y);
        super.draw(batch);
        update(delta);
    }
    public void collectBottle(Bottle bottle) {
        if (bottle != null && !this.hasBottle){
            setCollectibleBottle(bottle);
            // Lógica para coletar a garrafa
            RandomBottles.removeBottle(bottle); // Remove a garrafa coletada
            setHasBottle(true);
        }
    }

    public boolean hasBottle() {
        return hasBottle;
    }
    public void setHasBottle(boolean hasBottle) {
        this.hasBottle = hasBottle;
    }
    public void useOrDiscardBottle() {
        this.hasBottle = false;
    }

    public void setCollectibleBottle(Bottle bottle) {
        this.collectibleBottle = bottle;
    }

    public Bottle getCollectibleBottle() {
        return collectibleBottle;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }
    public void update(float delta) {
        boolean isMoving = playerInputProcessor.UP || playerInputProcessor.DOWN || playerInputProcessor.LEFT || playerInputProcessor.RIGHT;

        if (isMoving) {
            stateTime += delta;
        } else {
            stateTime = 0;
        }

        Animation<TextureRegion> animation = animations[frameRow];
        currentFrame = animation.getKeyFrame(isMoving ? stateTime : 0, true);

        setRegion(currentFrame);
        this.setPosition(x, y);
        playerHitbox.setPosition(x, y);

        if (playerInputProcessor.UP) {
            y += speed * delta;
            frameRow = 0;
        } else if (playerInputProcessor.DOWN) {
            y -= speed * delta;
            frameRow = 2;
        } else if (playerInputProcessor.LEFT) {
            x -= speed * delta;
            frameRow = 3;
        } else if (playerInputProcessor.RIGHT) {
            x += speed * delta;
            frameRow = 1;
        }else if (playerInputProcessor.ATTACK  && hasBottle) {
            Bottle collectedBottle = getCollectibleBottle();
            System.out.println("ATACANDO");
            System.out.println("Cor da garrada " + (collectedBottle.getBottleColor()));
            useOrDiscardBottle();
        }

        if (x < 0) {
            x = 0;
        } else if (x + FRAME_WIDTH > mapWidth) {
            x = mapWidth - FRAME_WIDTH;
        }

        if (y < 0) {
            y = 0;
        } else if (y + FRAME_HEIGHT > mapHeight) {
            y = mapHeight - FRAME_HEIGHT;
        }
    }
    public Rectangle getHitbox() {
        return playerHitbox;
    }
}
