package Player;

import Bottles.Bottle;
import Bottles.RandomBottles;
import Puddles.Puddle;
import Screens.GameScreen;
import Utils.Direction;
import Utils.AttackEffectHandler;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.ColorSplash;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import static Screens.GameScreen.player;
import static jdk.jfr.internal.handlers.EventHandler.duration;

public class Player extends Sprite {
    private float mapWidth, mapHeight;
    private PlayerInputProcessor playerInputProcessor;
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
    private boolean hasBottle;
    private Rectangle playerHitbox;
    private Rectangle playerHealth;
    private Bottle collectibleBottle;
    //private List<Sprite> spritesParaRenderizar = new ArrayList<>();

    private Direction currentDirection;

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private List<Puddle> puddles = new ArrayList<>();

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    private float invulnerabilityTime = 0;
    public Player(int mapWidth, int mapHeight, PlayerInputProcessor InputProcessor){
        super(ColorSplash.manager.<Texture>get("Characters/Mage.png"));
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
       //playerInputProcessor = new PlayerInputProcessor();
        //ColorSplash.addInputProcessor(playerInputProcessor);
        this.frameRow = 0;
        this.frameCol = 0;
        setRegion(frameCol * FRAME_WIDTH, frameRow * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
        this.x = 0;
        this.y = 0;
        this.setPosition(this.x, this.y);
        this.playerInputProcessor = InputProcessor;

        playerHitbox = new Rectangle(x, y, getWidth() - 30, getHeight() - 10);
        playerHealth = new Rectangle(x, y, getWidth()- 28, getHeight() -150);

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

        this.hasBottle = false;
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
            RandomBottles.removeBottle(bottle);
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
        System.out.println("ENTROU AQUII");

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
        playerHealth.setPosition(x, y+140);

        if (playerInputProcessor.UP) {
            y += speed * delta;
            frameRow = 0;
            currentDirection = Direction.UP;
        } else if (playerInputProcessor.DOWN) {
            y -= speed * delta;
            frameRow = 2;
            currentDirection = Direction.DOWN;
        } else if (playerInputProcessor.LEFT) {
            x -= speed * delta;
            frameRow = 3;
            currentDirection = Direction.LEFT;
        } else if (playerInputProcessor.RIGHT) {
            x += speed * delta;
            frameRow = 1;
            currentDirection = Direction.RIGHT;
        }else if (playerInputProcessor.ATTACK  && hasBottle) {
            attack();
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

        if (invulnerabilityTime > 0) {
            invulnerabilityTime -= delta;
        }
    }

    public void attack() {
        Bottle collectedBottle = getCollectibleBottle();
        System.out.println("ATACANDO");
        System.out.println("Cor da garrafa " + (collectedBottle.getBottleColor()));

        AttackEffectHandler.createAttackEffect(collectedBottle,currentDirection, this.getX(), this.getY(), puddles);
        useOrDiscardBottle();

    }
    public Rectangle getHitbox() {
        return playerHitbox;
    }
    public Rectangle getHealthBox() {
        return playerHealth;
    }
    public List<Puddle> getPuddles() {
        return puddles;
    }

    public void takeDamage(int damage) {
        if (invulnerabilityTime <= 0) {
            currentHealth -= damage;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
            invulnerabilityTime = 1.0f;
            updateHealthBar();
        }
    }

    private void updateHealthBar() {
        float healthPercent = (float) currentHealth / maxHealth;
        playerHealth.setWidth((getWidth() - 28) * healthPercent);
    }
}
