package Enemy;

import Bottles.Bottle;
import Bottles.RandomBottles;
import Puddles.Puddle;
import Screens.GameScreen;
import Utils.AttackEffectHandler;
import Utils.Direction1;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ColorSplash;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Sprite{
    private float mapWidth, mapHeight;
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
    private Rectangle player2Hitbox;
    private Rectangle player2Health;
    private Bottle collectibleBottle;
    //private List<Sprite> spritesParaRenderizar = new ArrayList<>();
//    public enum Direction {
//        UP, DOWN, LEFT, RIGHT
//    }
    private Direction1 currentDirection;

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private List<Puddle> puddles = new ArrayList<>();
    private EnemyInputProcessor player2InputProcessor;

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    private float invulnerabilityTime = 0;
    public Enemy(int mapWidth, int mapHeight, EnemyInputProcessor InputProcessor){
        super(ColorSplash.manager.<Texture>get("Characters/Fighter.png"));
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        //player2InputProcessor = new EnemyInputProcessor();
        //ColorSplash.addInputProcessor(player2InputProcessor);
        this.frameRow = 0;
        this.frameCol = 0;
        setRegion(frameCol * FRAME_WIDTH, frameRow * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
        this.x = 850;
        this.y = 0;
        this.setPosition(this.x, this.y);
        this.player2InputProcessor = InputProcessor;

        player2Hitbox = new Rectangle(x, y, getWidth() - 30, getHeight() - 10);
        player2Health = new Rectangle(x, y, getWidth()- 28, getHeight() -150);

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
        System.out.println("TA ENTRANDO AQUI ");

        boolean isMoving = player2InputProcessor.UP1 || player2InputProcessor.DOWN1 || player2InputProcessor.LEFT1 || player2InputProcessor.RIGHT1;

        if (isMoving) {
            stateTime += delta;
        } else {
            stateTime = 0;
        }

        Animation<TextureRegion> animation = animations[frameRow];
        currentFrame = animation.getKeyFrame(isMoving ? stateTime : 0, true);

        setRegion(currentFrame);
        this.setPosition(x, y);
        player2Hitbox.setPosition(x, y);
        player2Health.setPosition(x, y+140);

        if (player2InputProcessor.UP1) {
            y += speed * delta;
            frameRow = 0;
            currentDirection = Direction1.UP;
        } else if (player2InputProcessor.DOWN1) {
            y -= speed * delta;
            frameRow = 2;
            currentDirection = Direction1.DOWN;
        } else if (player2InputProcessor.LEFT1) {
            x -= speed * delta;
            frameRow = 3;
            currentDirection = Direction1.LEFT;
        } else if (player2InputProcessor.RIGHT1) {
            x += speed * delta;
            frameRow = 1;
            currentDirection = Direction1.RIGHT;
        }else if (player2InputProcessor.ATTACK1  && hasBottle) {
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

        AttackEffectHandler.createAttackEffect1(collectedBottle,currentDirection, this.getX(), this.getY(), puddles);
        useOrDiscardBottle();

    }

    public Rectangle getHitbox() {
        return player2Hitbox;
    }
    public Rectangle getHealthBox() {
        return player2Health;
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
        player2Health.setWidth((getWidth() - 28) * healthPercent);
    }
}
